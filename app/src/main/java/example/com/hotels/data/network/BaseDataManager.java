package example.com.hotels.data.network;

import example.com.hotels.data.network.parser.BaseParser;

public class BaseDataManager<T extends BaseParser> {

    protected APIServices apiServices;

    public BaseDataManager(APIServices apiServices) {
        this.apiServices = apiServices;
    }

}
