package aghazadeh.ahmad.Binders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


import aghazadeh.ahmad.business.BinderViewType;
import aghazadeh.ahmad.smsblocke.db.Roles;
import aghazadeh.ahmad.smsblocker.R;
import jp.satorufujiwara.binder.recycler.RecyclerBinder;

/**
 * Created by 890683 on 5/25/2016.
 */
public class RolesRecyclerBinder extends RecyclerBinder<BinderViewType> {

    private final Roles role;

    public RolesRecyclerBinder(Activity activity, Roles role) {
        super(activity, BinderViewType.ROLES_TYPE);
        this.role = role;
    }

    @Override
    public int layoutResId() {
        return R.layout.item_role;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.name.setText(role.getRoleName());
        holder.condition.setText(role.getCondition());
        holder.isActive.setChecked(role.getIsActive());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       TextView name;
       TextView condition;
       CheckBox isActive;
        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            condition = (TextView) view.findViewById(R.id.condition);
            isActive = (CheckBox) view.findViewById(R.id.is_active);
         }
    }
}


