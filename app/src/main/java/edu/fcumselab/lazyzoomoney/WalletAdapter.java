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

    // 動態載入頁面上的元件
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView walletImage;
        TextView walletName;
        TextView walletMoney;
        public ViewHolder(View view){
            super(view);
            walletImage = view.findViewById(R.id.icon);
            walletName = view.findViewById(R.id.name);
            walletMoney = view.findViewById(R.id.money);
        }
    }

    public WalletAdapter(List<WalletEntity> walletList){
        mWalletList=walletList;
    }
    @Override
    public WalletAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater.inflate() 可動態載入佈局 (wallet_item)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_item, parent,false);
        RecyclerView.ViewHolder holder = new ViewHolder(view);
        return (ViewHolder) holder;
    }

    @Override
    // 從 WalletEntity 中, 得到資料並顯示
    public void onBindViewHolder(WalletAdapter.ViewHolder holder, int position) {
        WalletEntity wallet=mWalletList.get(position);
        holder.walletImage.setImageResource(wallet.getImageId());
        holder.walletName.setText(wallet.getName());
        holder.walletMoney.setText(wallet.getMoney());
    }

    @Override
    public int getItemCount() {
        return mWalletList.size();
    }
}