/*
 * Copyright (C) 2017
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
package com.example.nurs.romebattlegroup;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.MainFractionContract;

public class DescriptionFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
	private DataAccess dbAccess;
	Cursor cursor;
	TextView desc;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.list_item_card, container, false);
		desc = v.findViewById(R.id.textViewDesc);
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		initRecyclerView();
	}

//	private void initRecyclerView() {
//		mRootView.setAdapter(new FakePageAdapter(20));
//	}

	public static Fragment newInstance(String frac, String squadName) {
		DescriptionFragment desFragment= new DescriptionFragment();
		Bundle bundle = new Bundle();
		bundle.putString("fraction",frac);
		bundle.putString("squadName", squadName);
		desFragment.setArguments(bundle);
		return desFragment;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
		return new CursorLoader(getContext()){
			@Override
			public Cursor loadInBackground() {
				dbAccess = DataAccess.getInstance(getContext());
				dbAccess.getDescription(getArguments().getString("fraction"), getArguments().getString("squadName"));
				return cursor;
			}
		};
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		cursor.moveToFirst();
		desc.setText(cursor.getString(cursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_DESCRIPTION)));
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

	}


//	private static class FakePageAdapter extends RecyclerView.Adapter<FakePageVH> {
//		private final int numItems;
//
//		FakePageAdapter(int numItems) {
//			this.numItems = numItems;
//		}
//
//		@Override
//		public FakePageVH onCreateViewHolder(ViewGroup viewGroup, int i) {
//			View itemView = LayoutInflater.from(viewGroup.getContext())
//				.inflate(R.layout.list_item_card, viewGroup, false);
//
//			return new FakePageVH(itemView);
//		}
//
//		@Override
//		public void onBindViewHolder(FakePageVH fakePageVH, int i) {
//			// do nothing
//		}
//
//		@Override
//		public int getItemCount() {
//			return numItems;
//		}
//	}

//	private static class FakePageVH extends RecyclerView.ViewHolder {
//		FakePageVH(View itemView) {
//			super(itemView);
//		}
//	}
}
