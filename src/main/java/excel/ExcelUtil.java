package excel;

/**
 * Created by admin on 2019/8/1.
 */

/**
 * Created by Administrator on 2018/4/23.
 */
public class ExcelUtil {


//    /**
//     * 验证excel文件
//     *
//     * @返回值：boolean
//     */
//    public static boolean validateExcel(String filePath) {
//        /** 检查文件名是否为空或者是否是Excel格式的文件 */
//
//        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
//            //errorInfo = "文件名不是excel格式";
//            return false;
//        }
//
//        /** 检查文件是否存在 */
//        File file = new File(filePath);
//        if (file == null || !file.exists()) {
//            //errorInfo = "文件不存在";
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 根据文件名读取excel文件
//     *
//     * @返回值：List
//     */
//    public static List<List<String>> read(String filePath,ExcleReadCondition excleReadCondition) {
//        /** 验证文件是否合法 */
//        if (!validateExcel(filePath)) {
//            //logger.info(errorInfo);
//            return null;
//        }
//        return read(new File(filePath),createIfNull(excleReadCondition));
//    }
//
//    /**
//     * @根据流读取Excel文件
//     * @返回值：List
//     */
//    public static List<List<String>> read(InputStream inputStream, boolean isExcel2003,ExcleReadCondition excleReadCondition) {
//        List<List<String>> dataLst = null;
//        Workbook wb = null;
//        try {
//            /** 根据版本选择创建Workbook的方式 */
//            if (isExcel2003) {
//                wb = new HSSFWorkbook(inputStream);
//            } else {
//                wb = new XSSFWorkbook(inputStream);
//            }
//            dataLst = read(wb,createIfNull(excleReadCondition));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                wb.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return dataLst;
//    }
//
//    /**
//     * @根据文件读取Excel文件
//     * @返回值：List 读取失败或文件没有内容，返回null
//     */
//    public static List<List<String>> read(File file,ExcleReadCondition excleReadCondition) {
//        List<List<String>> dataLst = null;
//        try (Workbook wb = WorkbookFactory.create(file)){
//            dataLst = read(wb,createIfNull(excleReadCondition));
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        } catch (InvalidFormatException e1) {
//            e1.printStackTrace();
//        }
//
//        return dataLst;
//    }
//
//    private static List<List<String>> read(Workbook wb,ExcleReadCondition excleReadCondition) {
//        /** 得到第一个shell */
//        Sheet sheet = wb.getSheetAt(excleReadCondition.sheetIndex);
//        FormulaEvaluator evaluator=wb.getCreationHelper().createFormulaEvaluator();
//        /** 得到Excel的行数 */
//        int totalRows = sheet.getPhysicalNumberOfRows();
//        int totalCells = 0;
//
//
////        if(rowNumber > totalRows || cellNumber > totalCells){
////            return dataLst;
////        }
//        if(!(totalRows>= excleReadCondition.rowIndex && totalCells>= excleReadCondition.cellIndex)){
//            return null;
//        }
//
//        /** 得到Excel的列数 */
//        if (totalRows >= 1 && sheet.getRow(excleReadCondition.rowIndex) != null) {
//            totalCells = sheet.getRow(excleReadCondition.rowIndex).getPhysicalNumberOfCells();
//        }
//
//        List<List<String>> dataLst = new ArrayList<List<String>>();
//        /** 循环Excel的行 */
//        for (int r = excleReadCondition.rowIndex; r < totalRows; r++) {
//            Row row = sheet.getRow(r);
//            if (row == null) {
//                continue;
//            }
//            List<String> rowLst = new ArrayList<String>();
//
//            /** 循环Excel的列 */
//            for (int c = excleReadCondition.cellIndex; c < totalCells; c++) {
//                Cell cell = row.getCell(c);
//                String cellValue = "";
//                if (null != cell) {
//                    if (null != cell) {
//                        CellType cellTypeEnum = cell.getCellTypeEnum();
//
//                        // 以下是判断数据的类型
//                        switch (cellTypeEnum.getCode()) {
//                            case 0: // 数字
//                                cellValue = (long) cell.getNumericCellValue() + "";
//                                break;
//
//                            case 1: // 字符串
//                                cellValue = cell.getStringCellValue().trim();
//                                if(excleReadCondition.isCase){
//                                    if(excleReadCondition.isToLower){
//                                        cellValue = cellValue.toLowerCase();
//                                    }else{
//                                        cellValue = cellValue.toUpperCase();
//                                    }
//                                }
//                                break;
//                            case 2: // 公式
//                                cellValue = getCellValue(evaluator.evaluate(cell));
//                                break;
//
//                            case 3: // 空值
//                                cellValue = "";
//                                break;
//                            case 4: // Boolean
//                                cellValue = cell.getBooleanCellValue() + "";
//                                break;
//                            case 5: // 故障
//                                cellValue = "非法字符";
//                                break;
//
//                            default:
//                                cellValue = "未知类型";
//                                break;
//                        }
//                    }
//                }
//                if(!StringUtils.isBlank(cellValue)){
//                    rowLst.add(cellValue);
//                }
//
//            }
//
//            /** 保存第r行的第c列 */
//            dataLst.add(rowLst);
//        }
//
//        return dataLst;
//    }
//
//    public static void wirte(String filePath,Map<String,List<List<Object>>> contentsMap){
////        List<String> rowNames = excleWriteCondition.getRowNames();
//        XSSFWorkbook wb = new XSSFWorkbook();
////        wb.setWorkbookType(XSSFWorkbookType.XLSX);
////        int width = 50;
//        for(Map.Entry<String,List<List<Object>>> entry : contentsMap.entrySet()){
//            XSSFSheet sheet = wb.createSheet(entry.getKey());
////            sheet.setColumnWidth(0, 256*width+184);//列宽
//            CellStyle style = wb.createCellStyle();  // 一个样式
//            style.setVerticalAlignment(VerticalAlignment.CENTER);    //设置垂直居中
//            style.setAlignment(HorizontalAlignment.CENTER); //水平居中
////            style.setWrapText(true);//自动换行
//            int i = 0 ;
//            for(List<Object> content : entry.getValue()){
//                XSSFRow row = sheet.createRow(i);
//                int j =0;
//                for(Object ob : content){
//                    XSSFCell cell = row.createCell(j);
//                    cell.setCellStyle(style);
//                    j++;
//                    if (ob == null){
//                        continue;
//                    }
//                    if(ob instanceof String){
//                        cell.setCellValue(ob.toString());
//                    }else if(ob instanceof Double){
//                        cell.setCellValue((Double)ob);
//                    }else if(ob instanceof Date){
//                        cell.setCellValue(((Date)ob));
//                    }else if(ob instanceof Boolean){
//                        cell.setCellValue((Boolean)ob);
//                    }else if(ob instanceof Calendar){
//                        cell.setCellValue(((Calendar)ob));
//                    }else if(ob instanceof Integer){
//                        cell.setCellValue((Integer)ob);
//                    }else{
//                        cell.setCellValue(ob.toString());
//                    }
//                }
//                i++;
//            }
//        }
//        try(FileOutputStream fileOut = new FileOutputStream(filePath)){
//            wb.write(fileOut);
//            fileOut.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    /**
//     * 是否是2003的excel，返回true是2003
//     */
//    public static boolean isExcel2003(String filePath) {
//        return filePath.matches("^.+\\.(?i)(xls)$");
//    }
//
//    /**
//     * 是否是2007的excel，返回true是2007
//     */
//    public static boolean isExcel2007(String filePath) {
//        return filePath.matches("^.+\\.(?i)(xlsx)$");
//    }
//
//
//    public static List<String> readIllegalFile(String filePath) {
//        List<List<String>> result = read(new File(filePath),createExcleCondition(4, 1, 1, true, true));
//        List<String> IlleaString = new ArrayList<>();
//        if (result != null) {
//            for (List<String> list : result) {
//                if (list != null && list.get(0) != null) {
//                    IlleaString.add(list.get(0));
//                }
//            }
//        }
//        return IlleaString;
//    }
//
//
//    private static ExcleReadCondition createIfNull(ExcleReadCondition excleReadCondition){
//        return excleReadCondition == null?new ExcleReadCondition(): excleReadCondition;
//    }
//
//
//    public static ExcleReadCondition createExcleCondition(int rowIndex, int cellIndex, int sheetIndex, boolean isCase, boolean isToLower){
//        return new ExcleReadCondition( rowIndex,  cellIndex,  sheetIndex,isCase,isToLower);
//    }
//
//    private static String getCellValue(CellValue cell) {
//        String cellValue = "";
//        switch (cell.getCellType()) {
//            case 1:
//                cellValue=cell.getStringValue();
//                break;
//
//            case 0:
//                cellValue=String.valueOf(cell.getNumberValue());
//                break;
//
//            default:
//                break;
//        }
//
//        return cellValue;
//    }
//
//    static class ExcleReadCondition {
//        private int rowIndex = 0;
//        private int cellIndex= 0;
//        private int sheetIndex= 0;
//        /**
//         * 字母是否转换大小写
//         */
//        private boolean isCase= false;
//        /**
//         * false:字母转换成大写 true：字母转换成大写
//         */
//        private boolean isToLower= false;
//
//        public ExcleReadCondition(int rowIndex, int cellIndex, int sheetIndex, boolean isCase, boolean isToLower) {
//            this.rowIndex = rowIndex;
//            this.cellIndex = cellIndex;
//            this.sheetIndex = sheetIndex;
//            this.isCase = isCase;
//            this.isToLower = isToLower;
//        }
//
//        public ExcleReadCondition() {
//        }
//
//        public int getRowIndex() {
//            return rowIndex;
//        }
//
//        public void setRowIndex(int rowIndex) {
//            this.rowIndex = rowIndex;
//        }
//
//        public int getCellIndex() {
//            return cellIndex;
//        }
//
//        public void setCellIndex(int cellIndex) {
//            this.cellIndex = cellIndex;
//        }
//
//        public int getSheetIndex() {
//            return sheetIndex;
//        }
//
//        public void setSheetIndex(int sheetIndex) {
//            this.sheetIndex = sheetIndex;
//        }
//
//        public boolean isCase() {
//            return isCase;
//        }
//
//        public void setCase(boolean aCase) {
//            isCase = aCase;
//        }
//
//        public boolean isToLower() {
//            return isToLower;
//        }
//
//        public void setToLower(boolean toLower) {
//            isToLower = toLower;
//        }
//    }
//
//    static class ExcleWriteCondition {
//        public List<String> rowNames;
//
//        public ExcleWriteCondition(List<String> rowNames) {
//            this.rowNames = rowNames;
//        }
//
//        public List<String> getRowNames() {
//            return rowNames;
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        //File xlsFile = new File("./instance/message/file/illegalName.xlsx");
//        //List<List<String>> list = read("./instance/message/file/illegalName.xlsx");
////        System.out.println((int)'Ａ');
////        ExcleReadCondition excleReadCondition = createExcleCondition(10, 0, 2, true, true);
////        List<List<String>> list = read(new File("D:\\work\\workSet\\fantidoc\\excel\\28提示语言表.xlsm"), excleReadCondition);
////        if (list != null) {
////            for (int i = 0; i < list.bodySize(); i++) {
////                System.out.print("第" + (i) + "行");
////                List<String> cellList = list.get(i);
////                for (int j = 0; j < cellList.bodySize(); j++) {
////                    // System.out.print("    第" + (j + 1) + "列值：");
////                    System.out.print("  " + cellList.get(j));
////                }
////                System.out.println();
////                //logger.info();
////            }
////        }
//
//
//    }




}
