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

import me.jessyan.lifecyclemodel.LifecycleModel;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * ================================================
 * {@link LifecycleModel} 可以用来存储以及管理数据, 也可以作为 MVP 模式中的 Presenter (只要实现 {@link LifecycleModel} 即可)
 * 或者 MVVM 模式中的 ViewModel (只要实现 {@link LifecycleModel} 即可) 用来管理业务逻辑, 更多使用方式等着你去发现
 * <p>
 * 这里可以使用 Rxjava, LiveData, 或者 {@link java.util.Observable} 乃至自己定义的监听器实现组件之间的通讯
 * 本质就是使用观察者模式, 使用什么方式实现, 看你的心情
 * <p>
 * Created by JessYan on 09/12/2017 11:17
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */

public class UserLifecycleModel implements LifecycleModel {
    private Subject<String, String> mSubject = PublishSubject.create();

    public void doAction(String s) {
        mSubject.onNext(s);
    }

    public void addAction(Action1<String> action) {
        mSubject.subscribe(action);
    }

    @Override
    public void onCleared() {

    }
}
