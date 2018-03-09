package example.com.hotels.data.network.parser;

import java.util.List;

public abstract class BaseParser<T> {

    public abstract List<T> getItemList();
    public abstract T getItem();

}
