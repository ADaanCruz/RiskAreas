package com.example.riskareas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;

import com.example.riskareas.model.Area;
import com.example.riskareas.model.Municipality;

import java.util.ArrayList;

public class SQLite {

    private Sql sql;
    private SQLiteDatabase db;

    public SQLite(Context context) {
        sql = new Sql(context);
    }

    public SQLite() {
    }

    public void open() {
        db = sql.getReadableDatabase();
        Log.i("SQLite", "Se abre conexión a la base de datos" + sql.getDatabaseName());
    }

    public void close() {
        db.close();
        Log.i("SQLite", "Se cierra conexión a la base de datos" + sql.getDatabaseName());
    }

    public boolean insert(Object object) {
        if (object instanceof Municipality) {
            return insertMunicipality((Municipality) object);
        } else if (object instanceof Area) {
            return insertArea((Area) object);
        } else {
            return false;
        }
    }

    private boolean insertMunicipality(Municipality municipality) {
        ContentValues content = new ContentValues();
        content.put("id", municipality.getId());
        content.put("name", municipality.getName());
        content.put("significance", municipality.getSignificance());
        content.put("header", municipality.getHeader());
        content.put("area", municipality.getArea());
        content.put("latitude", municipality.getLatitude());
        content.put("clime", municipality.getClime());
        content.put("location", municipality.getLocation());

        return (db.insert("municipalities", null, content) != (-1));
    }

    private boolean insertArea(Area area) {
        ContentValues content = new ContentValues();
        content.put("id", area.getId());
        content.put("id_municipality", area.getId_municipality());
        content.put("disaster", area.getDisaster());

        return (db.insert("areas", null, content) != (-1));
    }

    public int delete(String object, Editable id) {
        if (object.equals("Municipality")) {
            return deleteMunicipility(id);
        } else if (object.equals("Area")) {
            return deleteArea(id);
        } else {
            return 0;
        }
    }

    private int deleteMunicipility(Editable id) {
        return db.delete("municipilities", "id="+id, null);
    }

    private int deleteArea(Editable id) {
        return db.delete("areas", "id="+id, null);
    }

    public String update(Object object) {
        if (object instanceof Municipality) {
            return updateMuniciplity((Municipality)object);
        } else if (object instanceof Area) {
            return updateArea((Area) object);
        } else {
            return "Error";
        }
    }

    private String updateMuniciplity(Municipality municipality) {
        ContentValues content = new ContentValues();
        content.put("id", municipality.getId());
        content.put("name", municipality.getName());
        content.put("significance", municipality.getSignificance());
        content.put("header", municipality.getHeader());
        content.put("area", municipality.getArea());
        content.put("latitude", municipality.getLatitude());
        content.put("clime", municipality.getClime());
        content.put("location", municipality.getLocation());

        int cant = db.update("municipilities", content, "id="+municipality.getId(), null);
        if (cant == 1) {
            return "Modificado";
        } else {
            return "Error innesperado";
        }
    }

    private String updateArea(Area area) {
        ContentValues content = new ContentValues();
        content.put("id", area.getId());
        content.put("id_municipality", area.getId_municipality());
        content.put("disaster", area.getDisaster());

        int cant = db.update("areas", content, "id="+area.getId(), null);
        if (cant == 1) {
            return "Modificado";
        } else {
            return "Error innesperado";
        }
    }

    public ArrayList<Municipality> getMunicipalities(Cursor cursor) {
        ArrayList<Municipality> list = new ArrayList<>();
        Municipality municipality;
        if (cursor.moveToFirst()) {
            do {
                municipality = new Municipality(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                );

                list.add(municipality);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Area> getAreas(Cursor cursor) {
        ArrayList<Area> list = new ArrayList<>();
        Area area;
        if (cursor.moveToFirst()) {
            do {
                area = new Area(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2)
                );

                list.add(area);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Cursor getMunicipalities() {
        return db.rawQuery("SELECT * FROM municipalities", null);
    }

    public Cursor getAreas() {
        return db.rawQuery("SELECT * FROM areas", null);
    }

    public Cursor getMunicipality(int id) {
        return db.rawQuery("SELECT * FROM municipilities WHERE id=" + id, null);
    }

    public Cursor getArea(int id) {
        return db.rawQuery("SELECT * FROM areas WHERE id=" + id, null);
    }

    public ArrayList<String> getID(Cursor cursor) {
        ArrayList<String> list = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getInt(0) + "]\r\n";
                list.add(item);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
