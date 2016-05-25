package aghazadeh.ahmad.smsblocker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import aghazadeh.ahmad.DaoAPP;
import aghazadeh.ahmad.smsblocke.db.Roles;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RolesActivity extends AppCompatActivity {

    @BindView(R.id.name)   EditText name;
    @BindView(R.id.condition)   EditText condition;
    @BindView(R.id.is_active)   CheckBox isActive;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Roles roles=new Roles();
                    roles.setIsActive(isActive.isChecked());
                    roles.setName(name.getText().toString());
                    roles.setCondition(condition.getText().toString());
                    DaoAPP.getRolesDao().insertInTx(roles);

                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }catch (Exception e)
                {
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
    }

}
