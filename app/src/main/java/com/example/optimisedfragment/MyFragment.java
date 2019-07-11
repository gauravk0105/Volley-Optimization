package com.example.optimisedfragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyFragment extends Fragment
{
    long t1,t2;
    Button ford,ferrari,jaguar,acura;
    ProgressDialog pb;
    TextView tv1,tv2,tv3,tv4;
    Context cont;
    FragmentActivity myContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cont = context;
        myContext = (FragmentActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mylayout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //pb = new ProgressDialog(cont);
        ford = (Button) getActivity().findViewById(R.id.btn1);
        ferrari = (Button) getActivity().findViewById(R.id.btn2);
        jaguar = (Button) getActivity().findViewById(R.id.btn3);
        acura = (Button) getActivity().findViewById(R.id.btn4);
        tv1 = (TextView) getActivity().findViewById(R.id.tv1);
        tv2 = (TextView) getActivity().findViewById(R.id.tv2);
        tv3 = (TextView) getActivity().findViewById(R.id.tv3);
        tv4 = (TextView) getActivity().findViewById(R.id.tv4);
        ford.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SingletonQueue.getInstance(getActivity()).cancelRequest("ferrari");
//                SingletonQueue.getInstance(getActivity()).cancelRequest("jaguar");
//                SingletonQueue.getInstance(getActivity()).cancelRequest("acura");
                jsonParseFord();
            }
        });

        ferrari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SingletonQueue.getInstance(getActivity()).cancelRequest("ford");
//                SingletonQueue.getInstance(getActivity()).cancelRequest("jaguar");
//                SingletonQueue.getInstance(getActivity()).cancelRequest("acura");
                jsonParseFerrari();
            }
        });

        jaguar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SingletonQueue.getInstance(getActivity()).cancelRequest("ford");
//                SingletonQueue.getInstance(getActivity()).cancelRequest("ferrari");
//                SingletonQueue.getInstance(getActivity()).cancelRequest("acura");
                jsonParseJaguar();
            }
        });

        acura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SingletonQueue.getInstance(getActivity()).cancelRequest("ford");
//                SingletonQueue.getInstance(getActivity()).cancelRequest("ferrari");
//                SingletonQueue.getInstance(getActivity()).cancelRequest("jaguar");
                jsonParseAcura();
            }
        });
    }

    public void jsonParseFord()
    {
        //pb.show();
        tv1.setText("\n\nFord Details\n\n");
        String url = "https://api.myjson.com/bins/l15fb";
        //Log.d("Request FORD", "onRequest: Ford");
        t1 = System.currentTimeMillis();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(isAdded()) {
                        t2 = System.currentTimeMillis();
                        Toast.makeText(getActivity(), "Response Recieve", Toast.LENGTH_LONG).show();
                        //pb.dismiss();pb.cancel();
                       // Log.d("Response FORD", "onResponse: Ford "+(t2-t1));
                    }
                    Log.d("Response FORD", "onResponse: Ford "+(t2-t1));
                    JSONArray jsonArray = response.getJSONArray("Models");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        tv1.append((i+1)+"Model_name: "+json.getString("model_name")+" , "+"model_make_id: "+json.getString("model_make_id")+"\n\n");
                    }
                    Log.d("Length:",""+jsonArray.length());

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("Error",error.getMessage());
            }
        });
        SingletonQueue.getInstance(getActivity()).addToRequestQueue(request, "f");
    }

    public void jsonParseFerrari()
    {
        tv2.setText("\n\nFerrari Details\n\n");
        String url = "https://api.myjson.com/bins/19ht6v";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Response FERRIE", "onRequest: Ferrair");
                    JSONArray jsonArray = response.getJSONArray("Models");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        tv2.append((i+1)+"Model_name: "+json.getString("model_name")+" , "+"model_make_id: "+json.getString("model_make_id")+"\n\n");
                    }if(isAdded())
                        Toast.makeText(getActivity(),"Response Recieve",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        SingletonQueue.getInstance(getActivity()).addToRequestQueue(request, "f");
    }

    public void jsonParseJaguar()
    {
        tv3.setText("\n\nJaguar Details\n\n");
        String url = "https://api.myjson.com/bins/19fo13";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Response Jaigr", "onRequest: Jagir");
                    JSONArray jsonArray = response.getJSONArray("Models");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        tv3.append((i+1)+"Model_name: "+json.getString("model_name")+" , "+"model_make_id: "+json.getString("model_make_id")+"\n\n");
                    }if(isAdded())
                        Toast.makeText(getActivity(),"Response Recieve",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        SingletonQueue.getInstance(getActivity()).addToRequestQueue(request, "f");
    }

    public void jsonParseAcura()
    {
        tv4.setText("\n\nAcura Details\n\n");
        String url = "https://api.myjson.com/bins/1fmkpz";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Response ACURA", "onRequest: Acura");
                    JSONArray jsonArray = response.getJSONArray("Models");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        tv4.append((i+1)+"Model_name: "+json.getString("model_name")+" , "+"model_make_id: "+json.getString("model_make_id")+"\n\n");
                    }if(isAdded())
                        Toast.makeText(getActivity(),"Response Recieve",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        SingletonQueue.getInstance(getActivity()).addToRequestQueue(request, "f");
    }

    @Override
    public void onStop() {
        super.onStop();
        SingletonQueue.getInstance(getActivity()).cancelRequest("f");
    }
}
