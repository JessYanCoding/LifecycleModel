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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import me.jessyan.lifecyclemodel.LifecycleModelProviders;

/**
 * ================================================
 * BFragment 通过 {@link UserLifecycleModel#doAction(String)} 与其他组件开始通讯, (AFragment 与 BFragment 不存在耦合关系即可通讯以及共享数据)
 *
 * Created by JessYan on 09/12/2017 15:31
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */

public class BFragment extends Fragment {

    public static BFragment createBFragment(FragmentManager fragmentManager) {
        BFragment fragment = new BFragment();
        fragmentManager.beginTransaction().add(fragment, BFragment.class.getName()).commit();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        UserLifecycleModel lifecycleModel = LifecycleModelProviders.of(getActivity()).get(UserLifecycleModel.class.getName());
        lifecycleModel.doAction("JessYan");
    }
}
