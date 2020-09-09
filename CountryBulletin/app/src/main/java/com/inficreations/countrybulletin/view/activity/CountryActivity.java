package com.inficreations.countrybulletin.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.inficreations.countrybulletin.R;
import com.inficreations.countrybulletin.model.CountryModel;
import com.inficreations.countrybulletin.view.adapter.CountryAdapter;
import com.inficreations.countrybulletin.view.fragment.CategoryTagFragment;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity implements CountryAdapter.CountryOnClickListener {
    RecyclerView recyclerView;
    CountryAdapter countryAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference country = db.collection("country");

    ArrayList<CountryModel> countryModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        // get the reference of RecyclerView
        recyclerView = findViewById(R.id.country_recycler_view);
        recyclerView.setHasFixedSize(true);

        // set a GridLayoutManager with default vertical orientation
        GridLayoutManager gridLayout = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayout);

        // call the constructor of CustomAdapter to send the reference and data to Adapter
        countryAdapter = new CountryAdapter(this, countryModelArrayList, this);

        // set the Adapter to RecyclerView
        recyclerView.setAdapter(countryAdapter);

        countryAdapter.notifyDataSetChanged();

        setDataInFirebase();
        //getDataFromFirebase();
    }

    private void setDataInFirebase() {
        List<String> category = new ArrayList<>();
        category.add("business");
        category.add("entertainment");
        category.add("health");
        category.add("science");
        category.add("sports");
        category.add("technology");

        CountryModel in = new CountryModel("in", "India", "https://i.imgur.com/Tvxhtfd.png", category);
        countryModelArrayList.add(in);


        CountryModel ar = new CountryModel("ar", "Argentina", "https://i.imgur.com/eWMS1VG.png", category);
        CountryModel au = new CountryModel("au", "Australia", "https://i.imgur.com/Dd7uPPs.png", category);
        CountryModel at = new CountryModel("at", "Austria", "https://i.imgur.com/3WJh4zH.png", category);
        countryModelArrayList.add(ar);
        countryModelArrayList.add(au);
        countryModelArrayList.add(at);

        CountryModel be = new CountryModel();
        be.setCountryCode("be");
        be.setCountryName("Belgium");
        be.setCountryFlag("https://i.imgur.com/Vzia8LW.png");
        be.setCategory(category);
        countryModelArrayList.add(be);

        CountryModel br = new CountryModel();
        br.setCountryCode("br");
        br.setCountryName("Brazil");
        br.setCountryFlag("https://i.imgur.com/qlBjOkK.png");
        br.setCategory(category);
        countryModelArrayList.add(br);

        CountryModel bg = new CountryModel();
        bg.setCountryCode("bg");
        bg.setCountryName("Bulgaria");
        bg.setCountryFlag("https://i.imgur.com/CZU7q1O.png");
        bg.setCategory(category);
        countryModelArrayList.add(bg);

        CountryModel ca = new CountryModel("ca", "Canada", "https://i.imgur.com/wPcdOeL.png", category);
        CountryModel cn = new CountryModel("cn", "China", "https://i.imgur.com/qDyh3bb.png", category);
        CountryModel co = new CountryModel("co", "Colombia", "https://i.imgur.com/6ptbFqF.png", category);
        CountryModel cu = new CountryModel("cu", "Cuba", "https://i.imgur.com/Rx2wdPv.png", category);
        CountryModel cz = new CountryModel("cz", "Czech Republic", "https://i.imgur.com/R6hnOQh.png", category);
        CountryModel eg = new CountryModel("eg", "Egypt", "https://i.imgur.com/BQr8MiK.png", category);
        CountryModel fr = new CountryModel("fr", "France", "https://i.imgur.com/7mpDuwY.png", category);
        CountryModel de = new CountryModel("de", "Germany", "https://i.imgur.com/6K42CNR.png", category);
        CountryModel gr = new CountryModel("gr", "Greece", "https://i.imgur.com/mDSVoqM.png", category);
        CountryModel hk = new CountryModel("hk", "Hong Kong", "https://i.imgur.com/wdb1Twj.png", category);
        CountryModel hu = new CountryModel("hu", "Hungary", "https://i.imgur.com/CxscyHb.png", category);
        CountryModel id = new CountryModel("id", "Indonesia", "https://i.imgur.com/FElZgT3.png", category);
        CountryModel ie = new CountryModel("ie", "Ireland", "https://i.imgur.com/QS4vVwk.png", category);
        CountryModel il = new CountryModel("il", "Israel", "https://i.imgur.com/DafnQFL.png", category);
        CountryModel it = new CountryModel("it", "Italy", "https://i.imgur.com/TIDAo57.png", category);
        CountryModel jp = new CountryModel("jp", "Japan", "https://i.imgur.com/d1pYlSD.png", category);
        CountryModel lv = new CountryModel("lv", "Latvia", "https://i.imgur.com/ZQMvxlm.png", category);
        CountryModel lt = new CountryModel("lt", "Lithuania", "https://i.imgur.com/eHdhpZU.png", category);
        CountryModel my = new CountryModel("my", "Malaysia", "https://i.imgur.com/szznqKZ.png", category);
        CountryModel mx = new CountryModel("mx", "Mexico", "https://i.imgur.com/AQLKrC8.png", category);
        CountryModel ma = new CountryModel("ma", "Morocco", "https://i.imgur.com/hp5KPbn.png", category);
        CountryModel nl = new CountryModel("nl", "Netherlands", "https://i.imgur.com/RhyUpnY.png", category);
        CountryModel nz = new CountryModel("nz", "New Zealand", "https://i.imgur.com/yQxcQqT.png", category);
        CountryModel ng = new CountryModel("ng", "Nigeria", "https://i.imgur.com/USWd13A.png", category);
        CountryModel no = new CountryModel("no", "Norway", "https://i.imgur.com/yXpJc3N.png", category);
        CountryModel ph = new CountryModel("ph", "Philippines", "https://i.imgur.com/535lPBS.png", category);
        CountryModel pl = new CountryModel("pl", "Poland", "https://i.imgur.com/vuz90HU.png", category);
        CountryModel pt = new CountryModel("pt", "Portugal", "https://i.imgur.com/ux53lbs.png", category);
        CountryModel ro = new CountryModel("ro", "Romania", "https://i.imgur.com/qjjI93D.png", category);
        CountryModel ru = new CountryModel("ru", "Russia", "https://i.imgur.com/k4C4DNJ.png", category);
        CountryModel sa = new CountryModel("sa", "Saudi Arabia", "https://i.imgur.com/tylulyH.png", category);
        CountryModel rs = new CountryModel("rs", "Serbia", "https://i.imgur.com/6s7pltm.png", category);
        CountryModel sg = new CountryModel("sg", "Singapore", "https://i.imgur.com/9QCtbdh.png", category);
        CountryModel sk = new CountryModel("sk", "Slovakia", "https://i.imgur.com/Jyzx8CU.png", category);
        CountryModel si = new CountryModel("si", "Slovenia", "https://i.imgur.com/qN70m4L.png", category);
        CountryModel za = new CountryModel("za", "South Africa", "https://i.imgur.com/FNyyVBr.png", category);
        CountryModel kr = new CountryModel("kr", "South Korea", "https://i.imgur.com/ISsNw8Z.png", category);
        CountryModel se = new CountryModel("se", "Sweden", "https://i.imgur.com/jUbwEt0.png", category);
        CountryModel ch = new CountryModel("ch", "Switzerland", "https://i.imgur.com/O3nP1ZM.png", category);
        CountryModel tw = new CountryModel("tw", "Taiwan", "https://i.imgur.com/zxm56Cz.png", category);
        CountryModel th = new CountryModel("th", "Thailand", "https://i.imgur.com/9rkKf12.png", category);
        CountryModel tr = new CountryModel("tr", "Turkey", "https://i.imgur.com/42aIpds.png", category);
        CountryModel ae = new CountryModel("ae", "UAE", "https://i.imgur.com/SClIK9Y.png", category);
        CountryModel ua = new CountryModel("ua", "Ukraine", "https://i.imgur.com/5xNOVLZ.png", category);
        CountryModel gb = new CountryModel("gb", "United Kingdom", "https://i.imgur.com/l0KMBDq.png", category);
        CountryModel us = new CountryModel("us", "United States", "https://i.imgur.com/Z3XVzpS.png", category);
        CountryModel ve = new CountryModel("ve", "Venuzuela", "https://i.imgur.com/Fhyhy7E.png", category);
        countryModelArrayList.add(ca);
        countryModelArrayList.add(cn);
        countryModelArrayList.add(co);
        countryModelArrayList.add(cu);
        countryModelArrayList.add(cz);
        countryModelArrayList.add(eg);
        countryModelArrayList.add(fr);
        countryModelArrayList.add(de);
        countryModelArrayList.add(gr);
        countryModelArrayList.add(hk);
        countryModelArrayList.add(hu);
        countryModelArrayList.add(id);
        countryModelArrayList.add(ie);
        countryModelArrayList.add(il);
        countryModelArrayList.add(it);
        countryModelArrayList.add(jp);
        countryModelArrayList.add(lv);
        countryModelArrayList.add(lt);
        countryModelArrayList.add(jp);
        countryModelArrayList.add(my);
        countryModelArrayList.add(mx);
        countryModelArrayList.add(ma);
        countryModelArrayList.add(nl);
        countryModelArrayList.add(nz);
        countryModelArrayList.add(ng);
        countryModelArrayList.add(no);
        countryModelArrayList.add(ph);
        countryModelArrayList.add(pl);
        countryModelArrayList.add(pt);
        countryModelArrayList.add(ro);
        countryModelArrayList.add(ru);
        countryModelArrayList.add(sa);
        countryModelArrayList.add(rs);
        countryModelArrayList.add(sg);
        countryModelArrayList.add(sk);
        countryModelArrayList.add(si);
        countryModelArrayList.add(za);
        countryModelArrayList.add(se);
        countryModelArrayList.add(ch);
        countryModelArrayList.add(tw);
        countryModelArrayList.add(th);
        countryModelArrayList.add(tr);
        countryModelArrayList.add(ae);
        countryModelArrayList.add(ua);
        countryModelArrayList.add(gb);
        countryModelArrayList.add(us);
        countryModelArrayList.add(ve);

        for (int i = 0; i < countryModelArrayList.size(); i++) {
            CountryModel model = countryModelArrayList.get(i);
            country.document(model.getCountryCode()).set(model);
        }
    }

    private void getDataFromFirebase() {
        country.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots != null) {
                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                                CountryModel model = documentSnapshot.toObject(CountryModel.class);

                                System.out.println("countryModelArrayList " + model);
                                countryModelArrayList.add(model);
                                countryAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        MainActivity.countryCode = countryModelArrayList.get(position).getCountryCode();
        CategoryTagFragment categoryTagsFragment = new CategoryTagFragment(countryModelArrayList.get(position).getCategory());
        categoryTagsFragment.show(getSupportFragmentManager(), "CategoryFragment");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                alertDialog();
                break;
        }
        return true;
    }

    public void alertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Search");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                MainActivity.searchKey = input.getText().toString().trim();
                if (MainActivity.searchKey.length() == 0) {

                } else
                    startActivity();
            }
        });
        alert.show();
    }

    private void startActivity() {
        MainActivity.isComingFromSearch = true;
        startActivity(new Intent(this, MainActivity.class));
    }
}
