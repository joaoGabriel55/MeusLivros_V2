package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.quaresma.meuslivros_v2.BancoHelper;
import com.example.quaresma.meuslivros_v2.R;

import java.util.List;


public class LivroAdapter extends RecyclerView.Adapter {

    Context context;
    List<Livro> livroList;
    BancoHelper bancoHelper;

    public LivroAdapter(Context context, List<Livro> livroList) {
        this.context = context;
        this.livroList = livroList;
        this.bancoHelper = new BancoHelper(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        LivroHolder holder = new LivroHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        LivroHolder livroHolder = (LivroHolder) holder;
        final Livro livroEscolhido = livroList.get(position);
        livroHolder.textViewNome.setText(livroEscolhido.getTitulo());
        livroHolder.nota.setRating(livroEscolhido.getNota());
        livroHolder.remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"Trash", Toast.LENGTH_SHORT).show();
                Livro lv = livroList.get(position);
                lv.setTitulo(((LivroHolder) holder).textViewNome.getText().toString());
                lv.setNota(((LivroHolder) holder).nota.getRating());
                bancoHelper.delete(lv);
                livroList.remove(position);

                notifyItemRemoved(position);
//              notifyItemRangeChanged(position, livroList.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return livroList == null ? 0 : livroList.size();
    }

    public class LivroHolder extends RecyclerView.ViewHolder {

        final TextView textViewNome;
        final RatingBar nota;
        final ImageButton remover;

        public LivroHolder(View v) {
            super(v);
            textViewNome = v.findViewById(R.id.title2);
            nota = v.findViewById(R.id.nota);
            remover = v.findViewById(R.id.imageButton5);

        }


    }

}
