package met.local.cs330.pz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import met.local.cs330.pz.model.Pacijent;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Pacijent>listData;

    public RecyclerViewAdapter(List<Pacijent> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.izleceni_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Pacijent ld=listData.get(position);

        holder.jmbg.setText("JMBG: " + ld.getJmbg());
        holder.ime.setText("IME: " + ld.getIme());
        holder.prezime.setText("PREZIME: " + ld.getPrezime());
        holder.simptomi.setText("SIMPTOMI: " + ld.getSimptomi());
        holder.terapija.setText("TERAPIJA: " + ld.getTerapija());
        holder.izlecen.setText("IZLECEN: " + Boolean.toString(ld.isIzlecen()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private MaterialTextView jmbg, ime, prezime, simptomi, terapija, izlecen;

        public ViewHolder(View itemView) {
            super(itemView);

            jmbg = itemView.findViewById(R.id.listJmbg);
            ime = itemView.findViewById(R.id.listIme);
            prezime = itemView.findViewById(R.id.listPrezime);
            simptomi = itemView.findViewById(R.id.listSimptomi);
            terapija = itemView.findViewById(R.id.listTerapija);
            izlecen = itemView.findViewById(R.id.listIzlecen);

        }
    }
}