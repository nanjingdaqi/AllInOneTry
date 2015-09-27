package org.peace.allinone.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.peace.allinone.R;

/**
 * Created by peacepassion on 15/9/27.
 */
public class RViewHolder extends RecyclerView.ViewHolder {

  @InjectView(R.id.tv) public TextView tv;

  public RViewHolder(View itemView) {
    super(itemView);
    ButterKnife.inject(this, itemView);
  }

}
