package com.trust.tnighttalk.tool;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Trust on 2018/1/26.
 */

public class TrustTool {
    /**
     * 检查是否为json
     * @param value
     * @return
     */
    public static boolean checkIsJson(String value) {
        try {
            new JSONObject(value);
            return true;
        } catch (JSONException var2) {
            return false;
        }
    }
}
