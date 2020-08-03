package com.lexiang.wlutils.poi;

import java.util.ArrayList;
import java.util.Map;

public interface DownloadExcelAdaptor {

    ArrayList<Map<String, Object>> data(Map<String, String> titileMap, ArrayList<Map<String, Object>> date);

}
