package com.project.mvvmbasemodel.model;

public class ListItemModel {

    private String item1;
    private String item2;

    public ListItemModel(String item1, String item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public String getItem1() {
        return item1;
    }


    public String getItem2() {
        return item2;
    }


   /*
    private static List<String> contracts = new ArrayList<>();
   public static void clearCacheContracts() {
        contracts.clear();
    }
    public ArrayList<WeightListItemModel> mapWeightListItems( ) {

        ArrayList<WeightListItemModel> itemModels = new ArrayList<>(0);
        addAllContracts();
        int position = 0;
        for (String contract : contracts) {
            for (GetWeightListDataModel dataModel : getWeightListDataModels()) {
                if (contract.equals(dataModel.getContract())) {
                    itemModels.add(new WeightListItemModel(dataModel, (position % 2 == 0)));
                }
            }
            position++;
        }
        return itemModels;
    }

    private void addAllContracts() {
        for (GetWeightListDataModel dataModel : getWeightListDataModels()) {
            String contract = dataModel.getContract();
            if (!contracts.contains(contract)) {
                contracts.add(contract);
            }
        }
    }



        public void getWeightList(WeightListModel weightListModel, int pageNo) {

        if (weightListModel.isFromContract() && TextUtils.isEmpty(weightListModel.getContractId())) {
            mNavigator.toast("No contract found");
            return;
        }

        boolean isFirstPage = pageNo == 1;

        if (isFirstPage) {
            loaderDialogVisibility.setValue(true);
        }

        compositeDisposable.add(loadWeightListData(weightListModel,
                pageNo,
                weightListResponseModel -> {
                    if (isFirstPage) {
                        loaderDialogVisibility.setValue(false);
                    }
                    if (weightListResponseModel.isStatus()) {
                        if (isFirstPage) {
                            get_weightListData().setValue(weightListResponseModel.mapWeightListItems());
                        } else {
                            mNavigator.dismissPageLoader();
                            get__weightNextData().setValue(weightListResponseModel.mapWeightListItems());
                        }
                        mNavigator.updateWeightPages(weightListResponseModel.getLastPage(), weightListResponseModel.getCurrentPage(), false);

                    }
                }
                , throwable -> {
                    if (!isFirstPage) {
                        mNavigator.dismissPageLoader();
                        return;
                    }
                    checkError(throwable);
                }));
    }


 private boolean isShowContract(int position, WeightListItemModel currentItem) {
        boolean showContract = true;
        if (position > 1 && position < adapter.getListItems().size()) {
            WeightListItemModel itemModel = adapter.getListItems().get(position - 1);
            if (itemModel != null) {
                if (itemModel.getContract().equalsIgnoreCase(currentItem.getContract())) {
                    showContract = false;
                }
            }
        }
        return showContract;
    }


    */

}
