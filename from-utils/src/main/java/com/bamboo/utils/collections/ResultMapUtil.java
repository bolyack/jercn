package com.bamboo.utils.collections;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
public class ResultMapUtil {

    private final Map<Key<?>, Object> values = new HashMap<Key<?>, Object>();

    public <T> void put( Key<T> key, T value ) {
        values.put( key, value );
    }

    public <T> T get( Key<T> key ) {
        return key.type.cast( values.get( key ) );
    }

    public static Key key(String identifier, Class type ) {
        return new Key(identifier, type);
    }

    public static void main(String[] args) {
        ResultMapUtil mtc = new ResultMapUtil();

        String drParamDTO = new String("1111232qr2r");

        Key<String> key1 = new Key<String>( "same-id", String.class );
        mtc.put(key1, drParamDTO);//一个用于put
        String dto = mtc.get(key1);
        System.out.println(dto.toString());



        /////////////////////////保存集合//////////////////////////

        List<String> list = new ArrayList<String>();
        list.add(drParamDTO);
        Key<List> key2 = new Key<List>("ke1-id", List.class);
        mtc.put(key2, list);

        List<String> listDTP = mtc.get(key2);
        if (null != listDTP ) {
            System.err.println(listDTP.size());

            for (String d :listDTP) {
                System.err.println(d.toString());
            }

        }




    }

}