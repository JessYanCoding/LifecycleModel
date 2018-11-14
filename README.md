# LifecycleModel
[ ![Jcenter](https://img.shields.io/badge/Jcenter-v1.0.1-brightgreen.svg?style=flat-square) ](https://bintray.com/jessyancoding/maven/lifecyclemodel/1.0.1/link)
[ ![Build Status](https://travis-ci.org/JessYanCoding/LifecycleModel.svg?branch=master) ](https://travis-ci.org/JessYanCoding/LifecycleModel)
[ ![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-LifecycleModel-brightgreen.svg?style=flat-square) ](https://android-arsenal.com/details/1/6545)
[ ![API](https://img.shields.io/badge/API-14%2B-blue.svg?style=flat-square) ](https://developer.android.com/about/versions/android-4.0.html)
[ ![License](http://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square) ](http://www.apache.org/licenses/LICENSE-2.0)
[ ![Author](https://img.shields.io/badge/Author-JessYan-orange.svg?style=flat-square) ](https://www.jianshu.com/u/1d0c0bc634db)
[ ![QQ-Group](https://img.shields.io/badge/QQ%E7%BE%A4-455850365%20%7C%20301733278-orange.svg?style=flat-square) ](https://shang.qq.com/wpa/qunwpa?idkey=7e59e59145e6c7c68932ace10f52790636451f01d1ecadb6a652b1df234df753)

## The LifecycleModel class is designed to store and manage UI-related data in a lifecycle conscious way, the LifecycleModel class allows data to survive configuration changes such as screen rotations,  it also handles the communication of the Activity / Fragment with the rest of the application, base on [ViewModel](https://developer.android.google.cn/topic/libraries/architecture/viewmodel.html)

## Introduction
**LifecycleModel** 基于 Google 在 2017 年 I/O 大会上发布的 Android 架构组件中的 [**ViewModel**](https://developer.android.google.cn/topic/libraries/architecture/viewmodel.html), 可以帮助 **Activity** 和 **Fragment** 储存和管理一些与 UI 相关以及他们必需的数据, 避免数据在屏幕旋转或配置更改时发生的数据丢失, 还可以帮助开发者轻易实现 **Fragment** 与 **Fragment** 之间, **Activity** 与 **Fragment** 之间的通讯以及共享数据, 因为我看到 Google 让 **MVVM** 框架中的 **ViewModel** 具有了这些功能, 所以我也想让 **MVP** 框架中的 **Presenter**, 乃至其他更多的模块都具有这些功能, 所以 **LifecycleModel** 诞生了 

> [**框架的分析和思路**](https://juejin.im/post/5a31f6b951882503eb4b4b21)

## Lifecycle
![lifecycle](https://raw.githubusercontent.com/JessYanCoding/LifecycleModel/master/art/Lifecyclemodel-lifecycle.jpg)

## Download
``` gradle
 implementation 'me.jessyan:lifecyclemodel:1.0.1'
```

## Usage
### Step 1
```java
 public class UserLifecycleModel implements LifecycleModel {
         private int id;
 
         public UserLifecycleModel(int id) {
             this.id = id;
         }
         
         void doAction() {
         
         }
 
         @Override
         public void onCleared() {
             //release resources
         }
     }
```

### Step 2
```java
 //Put data
 LifecycleModelProviders.of(activity/fragment).put(UserLifecycleModel.class.getName(), new UserLifecycleModel(1));  

 //Get data
 LifecycleModelProviders.of(activity/fragment).get(UserLifecycleModel.class.getName());  
 
 //Remove data
 LifecycleModelProviders.of(activity/fragment).remove(UserLifecycleModel.class.getName());
```

### Communication of the Activity / Fragment
```java
 public class UserLifecycleModel implements LifecycleModel {
     private Subject<String, String> mSubject = PublishSubject.create();

     public void doAction(String s) {
         mSubject.onNext(s);
     }

     public void addAction(Action1<String> action) {
         mSubject.subscribe(action);
     }
 }
 
 public class AFragment extends Fragment {
     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         UserLifecycleModel lifecycleModel = LifecycleModelProviders.of(getActivity()).get(UserLifecycleModel.class.getName());
         lifecycleModel.addAction(new Action1<String>() {
             @Override
             public void call(String s) {
                 // Update the UI.
             }
         });
     }
 }
 
 public class BFragment extends Fragment {
     @Override
     public void onStart() {
         super.onStart();
         UserLifecycleModel lifecycleModel = LifecycleModelProviders.of(getActivity()).get(UserLifecycleModel.class.getName());
         lifecycleModel.doAction("JessYan");
     }
 }
```


## About Me
* **Email**: <jess.yan.effort@gmail.com>
* **Home**: <http://jessyan.me>
* **掘金**: <https://gold.xitu.io/user/57a9dbd9165abd0061714613>
* **简书**: <http://www.jianshu.com/u/1d0c0bc634db>

## License
```
 Copyright 2017, jessyan

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
