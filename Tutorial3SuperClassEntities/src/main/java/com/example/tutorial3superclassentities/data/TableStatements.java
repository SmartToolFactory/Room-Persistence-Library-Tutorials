package com.example.tutorial3superclassentities.data;

import android.content.Context;

public abstract class TableStatements {

    /*
     * TABLE NAMES
     */
    // Angle Meter
    public static final String TABLE_CLINOMETER_ANGLE = "table_clinometer_angle";
    public static final String TABLE_CLINOMETER_BUBBLE_LEVEL = "table_clinometer_bubble_level";
    // Laser Level
    public static final String TABLE_LASER_LEVEL_ANGLE = "table_laser_level_angle";
    public static final String TABLE_LASER_LEVEL_GALLERY = "table_laser_level_gallery";
    // Ruler
    public static final String TABLE_RULER_LENGTH = "table_ruler_length";
    public static final String TABLE_RULER_AREA = "table_ruler_area";
    // Protractor
    public static final String TABLE_PROTRACTOR = "table_protractor";
    // Color Detector
    public static final String TABLE_COLOR_DETECTOR = "table_color_detector";
    // Distance Meter


    /*
     * *** COLUMN NAMES FOR TABLES ***
     */

    // General Table Columns
    public static String COLUMN_ID = "id";
    public static String COLUMN_TITLE = "title";
    public static final String COLUMN_UNIT = "unit";
    public static String COLUMN_NOTE = "note";
    public static String COLUMN_DATE = "date";

    // Angle Measurement Columns
    public static String COLUMN_ANGLE = "angle";
    public static final String COLUMN_BUBLE_ANGLE_HORIZONTAL = "angle_horizontal";
    public static final String COLUMN_BUBBLE_ANGLE_VERTICAL = "angle_vertical";

    // Laser Level Columns
    public static String COLUMN_AZIMUTH = "azimuth";
    public static String COLUMN_PITCH = "pitch";
    public static String COLUMN_ROLL = "roll";
    public static String COLUMN_BEARING = "bearing";

    // Ruler Columns
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_AREA = "area";

    // Gallery Columns
    public static String COLUMN_GALLERY_NAME = "name";
    public static String COLUMN_GALLERY_ARTIST = "artist";
    public static String COLUMN_GALLERY_COPYRIGHT = "copyright";
    public static String COLUMN_GALLERY_COMMENT = "comment";
    public static String COLUMN_GALLERY_DESCRIPTION = "description";

    // Color Finder Columns
    public static final String COLUMN_COLOR_RED = "red";
    public static final String COLUMN_COLOR_GREEN = "green";
    public static final String COLUMN_COLOR_BLUE = "blue";
    public static final String COLUMN_COLOR_NAME = "name";
    public static final String COLUMN_COLOR_HEX = "hex";
    public static final String COLUMN_COLOR_HSV = "hsv";


    // ******************* ANGLEMETER SINGLE ANGLE TABLE *******************
    /**
     * Statement for creating angle measurement table: </br>
     * <p>
     * CREATE TABLE IF NOT EXISTS table_anglemeter_single (id INTEGER PRIMARY KEY
     * AUTOINCREMENT, title TEXT angle1 REAL, angle2 REAL difference
     * REAL, note TEXT, date INTEGER);
     */
    public static final String CREATE_TABLE_ANGLE_METER_SINGLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CLINOMETER_ANGLE
            + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_UNIT + " TEXT, "
            + COLUMN_ANGLE + " REAL, "
            + COLUMN_NOTE + " TEXT, "
            + COLUMN_DATE + " INTEGER);";

    public static final String[] SELECTED_COLUMNS_CLINOMETER_ANGLE = {COLUMN_ID, COLUMN_TITLE, COLUMN_UNIT, COLUMN_ANGLE,
            COLUMN_NOTE, COLUMN_DATE};


    // ******************* ANGLEMETER BUBBLE LEVEL TABLE *******************

    /**
     * Statement for creating bubble level table: </br>
     * <p>
     * CREATE TABLE IF NOT EXISTS table_anglemeter_bubble_level (id INTEGER PRIMARY KEY
     * AUTOINCREMENT, title TEXT, angle_horizontal REAL, angle_vertical
     * REAL, note TEXT, date INTEGER);
     */
    public static final String CREATE_TABLE_ANGLE_METER_BUBBLE_LEVEL = "CREATE TABLE IF NOT EXISTS " + TABLE_CLINOMETER_BUBBLE_LEVEL + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_UNIT + " TEXT, "
            + COLUMN_BUBLE_ANGLE_HORIZONTAL + " REAL, "
            + COLUMN_BUBBLE_ANGLE_VERTICAL + " REAL, "
            + COLUMN_NOTE + " TEXT, "
            + COLUMN_DATE + " INTEGER);";

    public static final String[] SELECTED_COLUMNS_ANGLE_METER_BUBBLE_LEVEL = {COLUMN_ID, COLUMN_TITLE, COLUMN_UNIT,
            COLUMN_BUBLE_ANGLE_HORIZONTAL, COLUMN_BUBBLE_ANGLE_VERTICAL, COLUMN_NOTE, COLUMN_DATE};


    // ******************* LASER LEVEL RECORD TABLE *******************
    // Table properties, column names for Angle Table;

    // Statement string for table creation
    /**
     * Statement for creating angle measurement table: </br>
     * <p>
     * CREATE TABLE IF NOT EXISTS table_smart_tools_laser (id INTEGER PRIMARY KEY
     * AUTOINCREMENT, title TEXT, angle REAL, azimuth REAL, pitch REAL, roll REAL,
     * bearing TEXT, note TEXT, date INTEGER);
     */
    public static final String CREATE_TABLE_LASER_LEVEL_ANGLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LASER_LEVEL_ANGLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_ANGLE + " REAL, "
            + COLUMN_AZIMUTH + " REAL, "
            + COLUMN_PITCH + " REAL, "
            + COLUMN_ROLL + " REAL, "
            + COLUMN_BEARING + " TEXT, "
            + COLUMN_NOTE + " TEXT, "
            + COLUMN_DATE + " INTEGER);";

    public static final String[] SELECTED_COLUMNS_LASER_LEVEL_ANGLE = {COLUMN_ID, COLUMN_TITLE, COLUMN_ANGLE,
            COLUMN_AZIMUTH, COLUMN_PITCH, COLUMN_ROLL, COLUMN_BEARING, COLUMN_NOTE, COLUMN_DATE};

    // ******************* SMART LASER LEVEL IMAGE GALLERY TABLE *******************

    /**
     * Statement for creating angle measurement table: </br>
     * <p>
     * CREATE TABLE IF NOT EXISTS table_smart_tools_laser_gallery (id INTEGER
     * PRIMARY KEY AUTOINCREMENT, image_name TEXT, date INTEGER, angle REAL, azimuth
     * REAL, pitch REAL, roll REAL, bearing TEXT, artist TEXT, copyright TEXT,
     * comment TEXT, description TEXT);
     */
    public static final String CREATE_TABLE_GALLERY_SMART_LASER_LEVEL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LASER_LEVEL_GALLERY + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_GALLERY_NAME + " TEXT, "
            + COLUMN_DATE + " INTEGER, "
            + COLUMN_ANGLE + " REAL, "
            + COLUMN_AZIMUTH + " REAL, "
            + COLUMN_PITCH + " REAL, "
            + COLUMN_ROLL + " REAL, "
            + COLUMN_BEARING + " TEXT, "
            + COLUMN_GALLERY_ARTIST + " TEXT, "
            + COLUMN_GALLERY_COPYRIGHT + " TEXT, "
            + COLUMN_GALLERY_COMMENT + " TEXT, "
            + COLUMN_GALLERY_DESCRIPTION + " TEXT);";

    public static final String[] SELECTED_COLUMNS_GALLERY_SMART_LASER_LEVEL = {COLUMN_ID, COLUMN_GALLERY_NAME,
            COLUMN_DATE, COLUMN_ANGLE, COLUMN_AZIMUTH, COLUMN_PITCH, COLUMN_ROLL, COLUMN_BEARING, COLUMN_GALLERY_ARTIST,
            COLUMN_GALLERY_COPYRIGHT, COLUMN_GALLERY_COMMENT, COLUMN_GALLERY_DESCRIPTION};

    public static void translateColumns(Context context) {

    }

    // ************** RULER LENGTH TABLE **************
    /**
     * Statement for creating ruler length measurement table: </br>
     * <p>
     * CREATE TABLE table_ruler (id INTEGER PRIMARY KEY AUTOINCREMENT, title
     * TEXT,unit TEXT, length REAL, note TEXT, date INTEGER);
     */
    public static final String CREATE_TABLE_RULER_LENGTH = "CREATE TABLE " + TABLE_RULER_LENGTH + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_UNIT + " TEXT, "
            + COLUMN_LENGTH + " REAL, "
            + COLUMN_NOTE + " TEXT, "
            + COLUMN_DATE + " INTEGER);";

    // SELECT LENGTH COLUMNS
    public static final String[] SELECTED_COLUMNS_RULER_LENGTH = {COLUMN_ID, COLUMN_TITLE, COLUMN_UNIT, COLUMN_LENGTH,
            COLUMN_NOTE, COLUMN_DATE};

    // ************** RULER AREA TABLE **************
    /**
     * Statement for creating ruler area measurement table: </br>
     * <p>
     * CREATE TABLE table_ruler (id INTEGER PRIMARY KEY AUTOINCREMENT, title
     * TEXT,unit TEXT, length REAL, width REAL, area REAL, note TEXT, date INTEGER);
     */
    public static final String CREATE_TABLE_RULER_AREA = "CREATE TABLE " + TABLE_RULER_AREA + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_UNIT + " TEXT, "
            + COLUMN_LENGTH + " REAL, "
            + COLUMN_WIDTH + " REAL, "
            + COLUMN_AREA + " REAL, "
            + COLUMN_NOTE + " TEXT, "
            + COLUMN_DATE + " INTEGER);";

    // SELECT AREA COLUMNS
    public static final String[] SELECTED_COLUMNS_RULER_AREA = {COLUMN_ID, COLUMN_TITLE, COLUMN_UNIT, COLUMN_LENGTH,
            COLUMN_WIDTH, COLUMN_AREA, COLUMN_NOTE, COLUMN_DATE};

    // ************** PROTRACTOR TABLE **************
    /**
     * Statement for creating ruler area measurement table: </br>
     * <p>
     * CREATE TABLE table_protractor (id INTEGER PRIMARY KEY AUTOINCREMENT, title
     * TEXT, unit TEXT, angle REAL, pitch REAL, roll REAL, note TEXT, date INTEGER);
     */
    public static final String CREATE_TABLE_PROTRACTOR = "CREATE TABLE " + TABLE_PROTRACTOR + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_UNIT + " TEXT, "
            + COLUMN_ANGLE + " REAL, "
            + COLUMN_PITCH + " REAL, "
            + COLUMN_ROLL + " REAL, "
            + COLUMN_NOTE + " TEXT, " + COLUMN_DATE
            + " INTEGER);";

    // SELECT PROTRACTOR COLUMNS
    public static final String[] SELECTED_COLUMNS_PROTRACTOR = {COLUMN_ID, COLUMN_TITLE, COLUMN_UNIT, COLUMN_ANGLE,
            COLUMN_PITCH, COLUMN_ROLL, COLUMN_NOTE, COLUMN_DATE};


    // Statement string for table creation
    /**
     * Statement for creating angle measurement table: </br>
     * <p>
     * CREATE TABLE tab (id INTEGER PRIMARY KEY AUTOINCREMENT, title
     * TEXT, red(TINYINT), green(TINYINT), blue(TINYINT), hex
     * TEXT, hsv VARCHAR(20), note TEXT, date INTEGER);
     */

    // ************** COLOR DETECTOR TABLE **************
    public static final String CREATE_TABLE_COLOR_DETECTOR = "CREATE TABLE " + TABLE_COLOR_DETECTOR + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_COLOR_RED + " TINYINT, "
            + COLUMN_COLOR_GREEN + " TINYINT, "
            + COLUMN_COLOR_BLUE + " TINYINT, "
            + COLUMN_COLOR_NAME + " TEXT, "
            + COLUMN_COLOR_HEX + " TEXT, "
            + COLUMN_COLOR_HSV + " TEXT, "
            + COLUMN_NOTE + " TEXT, "
            + COLUMN_DATE + " INTEGER);";

    public static final String[] SELECTED_COLUMNS_COLORS = {COLUMN_ID, COLUMN_TITLE, COLUMN_COLOR_RED,
            COLUMN_COLOR_GREEN, COLUMN_COLOR_BLUE, COLUMN_COLOR_NAME, COLUMN_COLOR_HEX, COLUMN_COLOR_HSV,
            COLUMN_NOTE, COLUMN_DATE};

}
