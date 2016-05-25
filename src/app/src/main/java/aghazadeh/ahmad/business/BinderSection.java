package aghazadeh.ahmad.business;

import jp.satorufujiwara.binder.Section;

/**
 * Created by 890683 on 5/25/2016.
 */
public enum BinderSection implements Section {

    ROLE_SECTION;

    @Override
    public int position() {
        return ordinal();
    }
}
