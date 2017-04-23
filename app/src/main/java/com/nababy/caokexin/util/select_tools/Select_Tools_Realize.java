package com.nababy.caokexin.util.select_tools;

import com.nababy.caokexin.bean.GouwucheBean;
import com.nababy.caokexin.bean.MingXingChanPinBean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Zhangdake on 2017/4/10.
 * 主要用于保存全选 反选状态 以及其它可能的用途
 */
public class Select_Tools_Realize implements SelectTools {

    /**
     * 保存选择状态的map
     */
    private HashMap<Object,Boolean> map;

    public Select_Tools_Realize() {
        map = new HashMap<Object, Boolean>();
    }

    //get方法
    public HashMap<Object, Boolean> getMap() {
        return map;
    }

    //set方法
    public void setMap(HashMap<Object, Boolean> map) {
        if(map==null){
            throw new NullPointerException();
        }
        this.map = map;
    }

    /**
     * 条目是否已经全部选中
     * @return true已全部选中 false未全部选中
     */
    @Override
    public boolean isSelectAll(){
        //map无数据则直接返回false
        if(map.isEmpty()){
            return false;
        }

        Set<Map.Entry<Object, Boolean>> set = map.entrySet();

        //使用迭代器
        Iterator<Map.Entry<Object, Boolean>> iterator = set.iterator();

        while(iterator.hasNext()){
            Map.Entry<Object, Boolean> entry = iterator.next();
            //有一个false 即返回false
            if(!entry.getValue()){
               return false;
            }
        }

        return true;
    }

    /**
     * 反选
     */
    @Override
    public void invertSelect(){
        Set<Map.Entry<Object, Boolean>> set = map.entrySet();
        //把value设置成相反的值
        for (Map.Entry<Object, Boolean> m : set) {
            m.setValue(!m.getValue());
        }
    }

    /**
     * 全选
     */
    @Override
    public void selectAll(){
        set_all_value(true);
    }

    /**
     * 全不选
     */
    @Override
    public void unselect(){
        set_all_value(false);
    }

    /**
     * @param flag 将map中的value全部设置成该值
     */
    @Override
    public void set_all_value(boolean flag){
        Set<Map.Entry<Object, Boolean>> set = map.entrySet();
        for (Map.Entry<Object, Boolean> m : set) {
            m.setValue(flag);
        }
    }

    /**
     * 获取相应条目的选中状态
     * @param t 泛型 那一条数据的选中状态
     * @return boolean 条目的选中状态
     */
    @Override
    public boolean getState(Object t){
        if(t==null){
            throw new NullPointerException();
        }
        return map.get(t);
    }

    /**
     * 改变条目状态
     * @param t 条目
     */
    @Override
    public void changeState(Object t) {
        map.put(t, !map.get(t));
    }

    /**
     * 向map中添加一个元素
     * @param t 泛型
     * @param flag 添加时的value值
     */
    @Override
    public void putItem(Object t, boolean flag){
        if (t==null){
            throw new NullPointerException();
        }
        map.put(t, flag);
    }

    /**
     * 向map中添加多条数据
     * @param list 需要添加的list数据集合
     * @param flag 默认的选中状态
     */
    @Override
    public void putItems(List list, boolean flag){
        if(list==null){
            throw new NullPointerException();
        }
        for (Object t : list) {
            map.put(t, flag);
        }
    }

    /**
     * 改变一个数据的选中状态 并检查map中所有的value是否都为true
     * @param t 需要改变状态的数据
     * @return 添加后map中的value值是否都为true 是返回true 否返回false
     */
    @Override
    public boolean clickItem(Object t){
        changeState(t);
        if(!getState(t)){
            return false;
        }
        return isSelectAll();
    }

    /**
     * 清空map中的所有信息
     */
    @Override
    public void clearItem(){
        map.clear();
    }

    @Override
    public void removeItem(Object o) {
        map.remove(o);
    }


    @Override
    public float getTotalPrice() {
        float total = 0;
        Set<Map.Entry<Object, Boolean>> set = map.entrySet();
        for (Map.Entry<Object, Boolean> m : set) {
            boolean k = m.getValue();
            if(k){
                GouwucheBean.CartItemList db = (GouwucheBean.CartItemList) m.getKey();
                total += Float.parseFloat(db.getPrice());
            }
        }
        total = (int)(total*100)/100f;
        return total;
    }

}
