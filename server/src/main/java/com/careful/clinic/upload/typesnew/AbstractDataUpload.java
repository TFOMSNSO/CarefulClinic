package com.careful.clinic.upload.typesnew;

import com.careful.clinic.upload.interfase.IDataUploadType;

import java.text.SimpleDateFormat;

public abstract class AbstractDataUpload  implements IDataUploadType {
    private SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
    private SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat df3 = new SimpleDateFormat("dd.MM.yyyy");



}
