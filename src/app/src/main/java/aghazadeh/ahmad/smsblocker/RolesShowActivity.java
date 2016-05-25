package aghazadeh.ahmad.smsblocker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import aghazadeh.ahmad.Binders.RolesRecyclerBinder;
import aghazadeh.ahmad.DaoAPP;
import aghazadeh.ahmad.business.BinderSection;
import aghazadeh.ahmad.business.BinderViewType;
import aghazadeh.ahmad.smsblocke.db.Roles;
import aghazadeh.ahmad.smsblocke.db.RolesDao;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter;

public class RolesShowActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.recycler_view)    RecyclerView recyclerView;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private final RecyclerBinderAdapter<BinderSection, BinderViewType> adapter
            = new RecyclerBinderAdapter<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_show);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

        List<Roles> roles= DaoAPP.getRolesDao().queryBuilder().list();
        for (Roles r :roles) {
            adapter.add(BinderSection.ROLE_SECTION,new RolesRecyclerBinder(this,r));
        }

        //adapter.addAll(BinderSection.ROLE_SECTION,roles);

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);




    }

}
