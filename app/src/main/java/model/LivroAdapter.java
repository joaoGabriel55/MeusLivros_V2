package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.quaresma.meuslivros_v2.BancoHelper;
import com.example.quaresma.meuslivros_v2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class LivroAdapter extends RecyclerView.Adapter {

    private static final int PENDING_TIMEOUT = 3000;
    private android.os.Handler handler = new android.os.Handler();
    HashMap<Livro, Runnable> pendingRunnables = new HashMap<>();
    Context context;
    List<Livro> livroList = new ArrayList<>();
    List<Livro> itemPending = new ArrayList<>();
    BancoHelper bancoHelper;

    public LivroAdapter(Context context, List<Livro> livroList) {
        this.context = context;
        this.livroList = livroList;
        this.bancoHelper = new BancoHelper(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.frame_layout, parent, false);
        LivroHolder holder = new LivroHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        LivroHolder livroHolder = (LivroHolder) holder;
        final Livro livroEscolhido = livroList.get(position);
        livroHolder.textViewNome.setText(livroEscolhido.getTitulo());
        livroHolder.nota.setRating(livroEscolhido.getNota());
        livroHolder.move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        if(itemPending.contains(livroEscolhido)){
            livroHolder.layoutNormal.setVisibility(View.GONE);
            livroHolder.layoutGone.setVisibility(View.VISIBLE);
            livroHolder.undobtn.setVisibility(View.VISIBLE);
            livroHolder.undobtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Runnable pendingRemovalRunnable = pendingRunnables.get(livroEscolhido);
                    pendingRunnables.remove(livroEscolhido);

                    if(pendingRemovalRunnable != null){
                        handler.removeCallbacks(pendingRemovalRunnable);
                    }
                    itemPending.remove(livroEscolhido);
                    notifyItemChanged(livroList.indexOf(livroEscolhido));


                }
            });
        } else {
            livroHolder.layoutNormal.setVisibility(View.VISIBLE);
            livroHolder.layoutGone.setVisibility(View.GONE);
            livroHolder.undobtn.setVisibility(View.GONE);
            livroHolder.undobtn.setOnClickListener(null);
        }

/*        livroHolder.remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"Trash", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Excluir");
                alert.setMessage("Deseja realmente remover esse livro?");
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Livro lv = livroList.get(position);
                        lv.setTitulo(((LivroHolder) holder).textViewNome.getText().toString());
                        lv.setNota(((LivroHolder) holder).nota.getRating());
                        bancoHelper.delete(lv);
                        livroList.remove(position);
                        notifyItemRemoved(position); //notifica o bindviewholder, responsavel por redesenhar a tela
                    }
                });

                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });

                AlertDialog criaAlerta = alert.create();
                criaAlerta.show();




//              notifyItemRangeChanged(position, livroList.size());

            }
        });*/

    }

    public void remove(int pos){
        Livro livro = livroList.get(pos);

        if(livroList.contains(livro)){
            bancoHelper.delete(livro);
            livroList.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    public void removerTempo(final int position){
        final Livro livro = livroList.get(position);

        if(!itemPending.contains(livro)){
            itemPending.add(livro);
            notifyItemChanged(position);
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(livroList.indexOf(livro));
                }
            };

            handler.postDelayed(pendingRemovalRunnable, PENDING_TIMEOUT);
            pendingRunnables.put(livro, pendingRemovalRunnable);
        }
    }

    public void mover(int fromPosition, int toPosition){

        if (fromPosition < toPosition){
            for (int i = fromPosition; i < toPosition; i++){
                Collections.swap(livroList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--){
                Collections.swap(livroList, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
        notifyItemChanged(toPosition);
        notifyItemChanged(fromPosition);

    }

    @Override
    public int getItemCount() {
        return livroList == null ? 0 : livroList.size();
    }

    public class LivroHolder extends RecyclerView.ViewHolder {

        final TextView textViewNome;
        final RatingBar nota;
        //final ImageButton remover;
        final LinearLayout layoutNormal;
        final LinearLayout layoutGone;
        final Button undobtn;
        final ImageButton move;

        public LivroHolder(View v) {
            super(v);
            textViewNome = v.findViewById(R.id.title2);
            nota = v.findViewById(R.id.nota);
            //remover = v.findViewById(R.id.imageButton5);
            layoutNormal = v.findViewById(R.id.layout_normal);
            layoutGone = v.findViewById(R.id.layout_gone);
            move = v.findViewById(R.id.move);
            undobtn = v.findViewById(R.id.undo_button);

        }


    }

}
