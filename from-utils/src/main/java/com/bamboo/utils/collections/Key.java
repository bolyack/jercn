package com.bamboo.utils.collections;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class Key<T> {

    public String id; // 主键ID，不能重复
    public Class<T> type;

    public Key(String id, Class<T> type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (null != obj && obj instanceof Key) {
            return this.id.equals(((Key)obj).id);
        }
        return super.equals(obj);
    }
}
