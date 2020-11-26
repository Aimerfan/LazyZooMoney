package edu.fcumselab.lazyzoomoney;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder>{

    private List<WalletEntity> mWalletList;
    //private OnItemClickListener mOnItemClickListener = null;
    //private OnItemLongClickListener mOnItemLongClickListener = null;

    
    // 動態載入頁面上的元件
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView walletImage;
        TextView walletName, walletMoney;
        public ViewHolder(View view){
            super(view);
            walletImage = view.findViewById(R.id.icon);
            walletName = view.findViewById(R.id.name);
            walletMoney = view.findViewById(R.id.money);
        }
    }

    public WalletAdapter(List<WalletEntity> walletList){
        mWalletList = walletList;
    }

    @Override
    public WalletAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater.inflate() 可動態載入佈局 (wallet_item)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_item, parent,false);
        RecyclerView.ViewHolder holder = new ViewHolder(view);
        return (ViewHolder) holder;
    }

    @Override
    /* 綁定資料與介面
       從 WalletEntity 中, 得到資料並顯示 */
    public void onBindViewHolder(WalletAdapter.ViewHolder holder, int position) {
        WalletEntity wallet = mWalletList.get(position);
        holder.walletImage.setImageResource(wallet.getImageId());
        holder.walletName.setText(wallet.getName());
        holder.walletMoney.setText(wallet.getMoney());

    /*    // 點擊事件註冊及分發
        if(null != mOnItemClickListener) {
            holder.walletName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(holder.walletName, position);
                }
            });
        }
        // 長按事件註冊及分發
        if(null != mOnItemLongClickListener) {
            holder.walletName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onLongClick(holder.walletName, position);
                }
            });
        }
     */
    }

    @Override
    public int getItemCount() {
        return mWalletList.size();
    }

/*
    // 設置點擊事件
    public void setOnItemClickListener(OnItemClickListener l) {
        this.mOnItemClickListener = l;
    }

    // 設置長按事件
    public void setOnItemLongClickListener(OnItemLongClickListener l) {
        this.mOnItemLongClickListener = l;
    }

    // 點擊事件接口
    public interface OnItemClickListener {
        void onClick(View parent, int position);
    }

    // 長按事件接口
    public interface OnItemLongClickListener {
        boolean onLongClick(View parent, int position);
    }
 */
}