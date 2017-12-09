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
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import me.jessyan.lifecyclemodel.LifecycleModelProviders;

/**
 * ================================================
 * 此框架的主要功能是帮助 Activity / Fragment 存储和管理与 UI 相关的数据, 并且可以避免 Activity 重建时导致的数据丢失
 * 使用方式与向 {@link HashMap} 中 {@link HashMap#put(Object, Object)}, {@link HashMap#get(Object)} 数据一致
 * Demo 中就不再做过多展示
 * <p>
 * Demo 中主要展示两个 Fragment 之间的通讯
 * <p>
 * Created by JessYan on 09/12/2017 11:17
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在 Fragment 获取 UserLifecycleModel 之前, 需要将它初始化以及存储起来
        LifecycleModelProviders.of(this).put(UserLifecycleModel.class.getName(), new UserLifecycleModel());
        AFragment.createAFragment(getSupportFragmentManager());
        BFragment.createBFragment(getSupportFragmentManager());
    }
}
