import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.expedro2ev.VideoJuegos

// Clase DatabaseHelper
class DatabaseHelper(context : Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "JuegosComprados.db"
        private const val TABLA_JUEGOS = "juegos"
        private const val KEY_ID = ""
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_VALORACION = "valoracion"
        private const val COLUMN_EMPRESA = "empresa"
        private const val COLUMN_FECHA = "fecha"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLA_JUEGOS ($KEY_ID INTEGER PRIMARY KEY, $COLUMN_NOMBRE TEXT, $COLUMN_VALORACION FLOAT, $COLUMN_EMPRESA TEXT, $COLUMN_FECHA INTEGER)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_JUEGOS")
        onCreate(db)
    }

    fun escribir(videoJuegos: VideoJuegos):Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, videoJuegos.getNombre())
            put(COLUMN_VALORACION, videoJuegos.getValoracion())
            put(COLUMN_EMPRESA, videoJuegos.getEmpresa())
            put(COLUMN_FECHA, videoJuegos.getFecha())

        }
        val id= db.insert(TABLA_JUEGOS, null, values)
        db.close()
        return id
    }



    @SuppressLint("Range")
    fun lectura(): ArrayList<VideoJuegos> {
        val lectura = ArrayList<VideoJuegos>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLA_JUEGOS"
        val cursor = db.rawQuery(selectQuery, arrayOf())
        if (cursor.moveToFirst()) {
            do {
                //val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val valoracion = cursor.getFloat(cursor.getColumnIndex(COLUMN_VALORACION))
                val empresa = cursor.getString(cursor.getColumnIndex(COLUMN_EMPRESA))
                val fecha = cursor.getInt(cursor.getColumnIndex(COLUMN_FECHA))
                lectura.add(VideoJuegos(nombre, valoracion, empresa, fecha))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lectura
    }

}