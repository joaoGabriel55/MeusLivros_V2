package model;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.quaresma.meuslivros_v2.R;

import java.util.List;

/**
 * Created by Quaresma on 24/09/2017.
 */

public class LivroImgAdapter extends PagerAdapter {

    Context context;
    List<Livro> livroList;

    public LivroImgAdapter(Context context, List<Livro> livroList) {
        this.context = context;
        this.livroList = livroList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.img_inflate, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.imagemLivro);
        img.setImageResource((livroList.get(position).getImg()));
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return livroList !=null ? livroList.size() : 0;
    }

    //relacionando view com o objeto passado.
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return livroList.get(position).getTitulo();
    }
}
