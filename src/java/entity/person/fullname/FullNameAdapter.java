/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.fullname;

/**
 *
 * @author zOzDarKzOz
 */
public class FullNameAdapter implements FullNameItf {

    private AdvancedFullNameItf advFullNanmeItf;
    private boolean isMatche = false;
    private static final String STR_REGEX = "/.{0,}(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ"
            + "|ắ|ặ|ẳ|ẵ|è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ|ì|í|ị|ỉ|ĩ|ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ"
            + "|ơ|ờ|ớ|ợ|ở|ỡ|ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ|ỳ|ý|ỵ|ỷ|ỹ|đ"
            + "|À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ|È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ|Ì|Í|Ị"
            + "|Ỉ|Ĩ|Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ|Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ|Ỳ"
            + "|Ý|Ỵ|Ỷ|Ỹ|Đ){1,}.{0,}/g";

    public FullNameAdapter(String firstName, String middleName, String lastName) {
        try {
            String fn = firstName.trim() + " " + middleName.trim() + " " + lastName.trim();
            isMatche = fn.matches(STR_REGEX);
            if (isMatche) {
                advFullNanmeItf = new ViFullName();
            } else {
                advFullNanmeItf = new EnFullName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFullName(String firstName, String middleName, String lastName) {
        try {
            if (isMatche) {
                return advFullNanmeItf.getViFullName(firstName, middleName, lastName);
            } else {
                return advFullNanmeItf.getEnFullName(firstName, middleName, lastName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
