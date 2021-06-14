package common.survin_it.notesapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.Myspace_pdfActivity;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;

public class BottomSheetDialogShare extends BottomSheetDialogFragment implements View.OnClickListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout ll_share,ll_share1,ll_krishan,ll_krishan1,ll_createpdf;
    TextView tv_remove,tv_remove1;
    ImageView iv_cross;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout_share,
                container, false);
        ll_share = v.findViewById(R.id.ll_share);
        ll_share1 = v.findViewById(R.id.ll_share1);
        ll_krishan = v.findViewById(R.id.ll_krishan);
        ll_krishan1 = v.findViewById(R.id.ll_krishan1);
        tv_remove = v.findViewById(R.id.tv_remove);
        tv_remove1 = v.findViewById(R.id.tv_remove1);
        ll_createpdf = v.findViewById(R.id.ll_createpdf);
        iv_cross = v.findViewById(R.id.iv_cross);
        ll_share.setOnClickListener(this);
        tv_remove.setOnClickListener(this);
        tv_remove1.setOnClickListener(this);
        iv_cross.setOnClickListener(this);
        ll_createpdf.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_share:
                if (ll_share1.getVisibility()==View.VISIBLE)
                {
                    ll_share1.setVisibility(View.GONE);

                }
                else
                {
                    ll_share1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_remove:
                if (ll_krishan.getVisibility()==View.VISIBLE)
                {
                    ll_krishan.setVisibility(View.GONE);

                }
                else
                {
                    ll_krishan.setVisibility(View.VISIBLE);
                }
              break;
                case R.id.tv_remove1:
                if (ll_krishan1.getVisibility()==View.VISIBLE)
                {
                    ll_krishan1.setVisibility(View.GONE);

                }
                else
                {
                    ll_krishan1.setVisibility(View.VISIBLE);
                }
              break;
            case R.id.iv_cross:
                getDialog().dismiss();
                break;
                case R.id.ll_createpdf:
             Intent i=new Intent(getContext(), Myspace_pdfActivity.class);
             startActivity(i);
                break;
        } }

}

