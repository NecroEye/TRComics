package com.muratcangzm.trcomics.fragments.fragmentAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.muratcangzm.trcomics.fragments.DescriptionFragment;
import com.muratcangzm.trcomics.fragments.EpisodeFragment;
import com.muratcangzm.trcomics.models.ComicModel;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final ComicModel model;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ComicModel model) {
        super(fragmentManager, lifecycle);
        this.model = model;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 0:
                return new DescriptionFragment(model);
            case 1:
                return new EpisodeFragment(model);
            default:
                throw new IllegalArgumentException("Geçersiz pozisyon" + position);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
