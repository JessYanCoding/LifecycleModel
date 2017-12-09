/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.jessyan.lifecyclemodel.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import me.jessyan.lifecyclemodel.LifecycleModelProviders;
import rx.functions.Action1;

/**
 * ================================================
 * AFragment 通过 {@link UserLifecycleModel#addAction(Action1)} 注册需要与其他组件通讯的事件以及逻辑
 *
 * Created by JessYan on 09/12/2017 15:31
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */

public class AFragment extends Fragment {

    public static AFragment createAFragment(FragmentManager fragmentManager) {
        AFragment fragment = new AFragment();
        fragmentManager.beginTransaction().add(fragment, AFragment.class.getName()).commit();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserLifecycleModel lifecycleModel = LifecycleModelProviders.of(getActivity()).get(UserLifecycleModel.class.getName());
        lifecycleModel.addAction(new Action1<String>() {
            @Override
            public void call(String s) {
                // Update the UI.
                Log.d("AFragment", s);
            }
        });
    }
}
