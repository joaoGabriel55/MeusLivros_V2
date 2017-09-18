package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.quaresma.meuslivros_v2.R;
import com.example.quaresma.meuslivros_v2.holder.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public class LivroAdapter extends BaseAdapter{

    Context context;
    List<Livro> listaLivros;


    public LivroAdapter(Context context, List<Livro> listaLivros) {
        this.context = context;
        this.listaLivros = listaLivros;

    }



    @Override
    public int getCount() {
        return listaLivros != null ? listaLivros.size(): 0;
    }

    @Override
    public Object getItem(int i) {
        return listaLivros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view;
        ViewHolder holder;

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_view, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        }else{
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        //Preenche os dados do livro
        Livro livroescolhido = listaLivros.get(i);
        holder.textViewTitulo.setText(livroescolhido.getTitulo());
        holder.textViewAutor.setText(livroescolhido.getTitulo());
        holder.checkBox.setChecked(livroescolhido.isLido());
        return view;
    }

    public void updateItens(ArrayList<Livro> itens) {
        this.listaLivros = itens;
        notifyDataSetChanged();
    }
}
