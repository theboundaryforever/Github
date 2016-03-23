/*
 * Copyright (C) 2016 david.wei (lighters)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lighters.github.ui.repo.RepoListPresenter;

import android.util.Log;
import com.lighters.github.common.di.PerActivity;
import com.lighters.github.data.model.net.RepoEntity;
import com.lighters.github.domain.viewdata.repo.RepoListViewData;
import com.lighters.github.ui.base.IBaseView;
import com.lighters.github.ui.base.IBasePresenter;
import com.lighters.github.ui.repo.view.IRepoListView;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by david on 16/3/23.
 * Email: huangdiv5@gmail.com
 * GitHub: https://github.com/david-wei
 */

@PerActivity
public class RepoListPresenter implements IBasePresenter<IRepoListView> {

    RepoListViewData mRepoListViewData;

    IRepoListView mView;

    @Inject
    public RepoListPresenter(RepoListViewData viewData) {
        mRepoListViewData = viewData;
    }

    @Override
    public void setView(IRepoListView dataView) {
        mView = dataView;
    }

    public void loadData() {
        mRepoListViewData.mUserName = "david-wei";
        mRepoListViewData.fetch().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<RepoEntity>>() {
                @Override
                public void onCompleted() {
                    Log.d("test", "complete");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("test", "error");
                }

                @Override
                public void onNext(List<RepoEntity> list) {
                    Log.d("test", "next");
                    mView.renderRepoList(list);
                }
            });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
