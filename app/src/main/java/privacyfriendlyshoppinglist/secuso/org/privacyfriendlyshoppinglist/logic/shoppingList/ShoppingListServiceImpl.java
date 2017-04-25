package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList;

import android.content.Context;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
//import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.comparators.PFAComparators;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.DB;
//import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.utils.DateUtils;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.StringUtils;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductItem;
//import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.impl.comparators.ListsComparators;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListServiceImpl implements ShoppingListService
{

    private ShoppingListDao shoppingListDao;
    private ShoppingListConverter shoppingListConverter;
    private Context context;

    @Inject
    public ShoppingListServiceImpl(
            ShoppingListDao shoppingListDao,
            ShoppingListConverter shoppingListConverter
    )
    {
        this.shoppingListDao = shoppingListDao;
        this.shoppingListConverter = shoppingListConverter;
    }

    @Override
    public void setContext(Context context, DB db)
    {
        shoppingListDao.setContext(context, db);
        shoppingListConverter.setContext(context, db);
        this.context = context;
    }

    @Override
    public Observable<Void> saveOrUpdate(ListItem item)
    {
        Observable<Void> observable = Observable
                .fromCallable(() -> saveOrUpdateSync(item))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    @Override
    public Void saveOrUpdateSync(ListItem item)
    {
        if ( StringUtils.isEmpty(item.getListName()) )
        {
            item.setListName(context.getResources().getString(R.string.default_list_name));
        }
        ShoppingListEntity entity = new ShoppingListEntity();
        shoppingListConverter.convertItemToEntity(item, entity);
        Long id = shoppingListDao.save(entity);
        item.setId(id.toString());
        return null;
    }

    @Override
    public Observable<ListItem> getById(String id)
    {
        Observable<ListItem> observable = Observable
                .fromCallable(() -> getByIdSync(id))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private ListItem getByIdSync(String id)
    {
        ListItem item = new ListItem();
        ShoppingListEntity entity = shoppingListDao.getById(Long.valueOf(id));
        shoppingListConverter.convertEntityToItem(entity, item);
        return item;
    }

    @Override
    public ShoppingListEntity getEntityByIdSync(String id)
    {
        return shoppingListDao.getById(Long.valueOf(id));
    }

    @Override
    public Observable<Void> deleteById(String id)
    {
        Observable<Void> observable = Observable
                .fromCallable(() -> deleteByIdSync(id))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private Void deleteByIdSync(String id)
    {
        shoppingListDao.deleteById(Long.valueOf(id));
        return null;
    }

    @Override
    public Observable<ListItem> getAllListItems()
    {
        Observable<ListItem> observable = Observable
                .defer(() -> Observable.from(getAllListItemsSync()))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private List<ListItem> getAllListItemsSync()
    {
        List<ListItem> listItem = new ArrayList<>();
        Observable
                .from(shoppingListDao.getAllEntities())
                .map(this::getItem)
                .subscribe(item -> listItem.add(item));
        return listItem;
    }

    @Override
    public Observable<String> deleteSelected(List<ListItem> shoppingListItems)
    {
        Observable<String> observable = Observable
                .defer(() -> Observable.from(deleteSelectedSync(shoppingListItems)))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private List<String> deleteSelectedSync(List<ListItem> shoppingListItems)
    {
        List<String> deletedIds = new ArrayList<>();
        Observable
                .from(shoppingListItems)
                .filter(item -> item.isSelected())
                .subscribe(
                        item ->
                        {
                            String id = item.getId();
                            deleteByIdSync(id);
                            deletedIds.add(id);
                        }
                );
        return deletedIds;
    }

    @Override
    public List<ListItem> moveSelectedToEnd(List<ListItem> shoppingListItems)
    {
        List<ListItem> nonSelectedItems = new ArrayList<>();
        Observable
                .from(shoppingListItems)
                .filter(item -> !item.isSelected())
                .subscribe(item -> nonSelectedItems.add(item));

        List<ListItem> selectedItems = new ArrayList<>();
        Observable
                .from(shoppingListItems)
                .filter(item -> item.isSelected())
                .subscribe(item -> selectedItems.add(item));

        nonSelectedItems.addAll(selectedItems);
        shoppingListItems = nonSelectedItems;
        return shoppingListItems;
    }

//    @Override
//    public void sortList(List<ListItem> lists, String criteria, boolean ascending)
//    {
//        if ( PFAComparators.SORT_BY_NAME.equals(criteria) )
//        {
//            Collections.sort(lists, ListsComparators.getNameComparator(ascending));
//        }
//        else if ( PFAComparators.SORT_BY_PRIORITY.equals(criteria) )
//        {
//            Collections.sort(lists, ListsComparators.getPriorityComparator(ascending));
//        }
//
//    }

    @Override
    public String getShareableText(ListItem listItem, List<ProductItem> productItems)
    {
        StringBuilder sb = new StringBuilder();

        sb.append(listItem.getListName());
        sb.append("\n");

        if ( productItems != null && !productItems.isEmpty() )
        {
            Observable.from(productItems)
                    .filter(item -> !item.isChecked())
                    .subscribe(item ->
                    {
                        sb
                                .append("- ")
                                .append("[ ")
                                .append(item.getQuantity())
                                .append(" ] ")
                                .append(item.getProductName())
                                .append("\n");
                    });
        }
        else
        {
            sb.append(context.getResources().getString(R.string.no_products));
        }

        if ( !StringUtils.isEmpty(listItem.getNotes()) )
        {
            sb.append("\n");
            sb.append(listItem.getNotes());
        }

        return sb.toString();
    }

    private ListItem getItem(ShoppingListEntity entity)
    {
        ListItem item = new ListItem();
        shoppingListConverter.convertEntityToItem(entity, item);
        return item;
    }
}
