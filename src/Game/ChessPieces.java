
package Game;

/**
 * 
 * @author Veerle
 *
 */

public class ChessPieces {

    protected int finalDesRow = 0;
    protected int finalDesColumn = 0;
    protected String strErrorMsg = "";

    public ChessPieces () {}
    
    //Checks the cell to make sure it is empty
    private boolean checkAxisMove (int newRow, int newColumn, int[][] playerMatrix) {

        //If not empty
        if (playerMatrix[newRow][newColumn] != 0) {
            strErrorMsg = "A piece is blocking the route"; //Error message
            return false;
        }
        return true;
    }
    
    //Method for checking the path to the destination and making sure nothing is in the way
    protected boolean axisMove (int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix, boolean straightAxis) {

        //Moving along a straight axis (rock, queen)
        if (straightAxis) {

            //Moving along the same column
            if ((startColumn == desColumn) && (startRow != desRow)) {

                //Moving N
                if (desRow < startRow) {     
                
                    //Checks each cell between the start row - 1 (since don't need to check the cell it is in) to the destination cell
                    for (int newRow = (startRow - 1); newRow > desRow; newRow--) {
                    
                        //Checks the cell is empty
                        if (!checkAxisMove(newRow, desColumn, playerMatrix)) {
                            return false;
                        }
                    }
                } else { //Moving S
                
                    for (int newRow = (startRow + 1); newRow < desRow; newRow++) {
                        if (!checkAxisMove(newRow, desColumn, playerMatrix)) {
                            return false;
                        }
                    }
                }
            } else if ((startRow == desRow) && (startColumn != desColumn)) { //Moving along the same row
            
                //Moving W
                if (desColumn < startColumn) {
                    for (int newColumn = (startColumn - 1); newColumn > desColumn; newColumn--) {
                        if (!checkAxisMove(desRow, newColumn, playerMatrix)) {
                            return false;
                        }
                    }
                } else {//Moving E
                
                    for (int newColumn = (startColumn + 1); newColumn < desColumn; newColumn++) {
                        if (!checkAxisMove(desRow, newColumn, playerMatrix)) {
                            return false;
                        }
                    }
                }
            } else {//If moved diagonally
            
                strErrorMsg = "Should not see this error message";
                return false;
            }
        } else {//Moving diagonal (bishop/queen)
       
            //Default error message
            strErrorMsg = "The destination is not on the same diagonal line"; 
            int newColumn = 0;

            //If moved NW
            if (desRow < startRow && desColumn < startColumn) {
            
                //The number of cells moved horizontal should equal the number of cells moved vertical
                if ((desRow - startRow) == (desColumn - startColumn)) {
                
                    for (int newRow = (startRow - 1); newRow > desRow; newRow--) {
                    
                        newColumn = startColumn - (startRow - newRow);
                        
                        if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else if (desRow < startRow && desColumn > startColumn) { //If moved NE
            
                if ((desRow - startRow) == (startColumn - desColumn)) {

                    for (int newRow = (startRow - 1); newRow > desRow; newRow--) {

                        newColumn = startColumn + (startRow - newRow);

                        if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else if (desRow > startRow && desColumn < startColumn) {//If moved SW
            
                if ((startRow - desRow) == (desColumn - startColumn)) {

                    for (int newRow = (startRow + 1); newRow < desRow; newRow++) {

                        newColumn = startColumn - (newRow - startRow);

                        if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else if (desRow > startRow && desColumn > startColumn) { //If moved SE
            
                if ((startRow - desRow) == (startColumn - desColumn)) {

                    for (int newRow = (startRow + 1); newRow < desRow; newRow++) {

                        newColumn = startColumn + (newRow - startRow);

                        if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else { //Not a diagonal move
            
                strErrorMsg = "Should never see this error message";
                return false;
            }
        }       

        finalDesRow = desRow;
        finalDesColumn = desColumn;

        return true;
    }

    public int getDesRow (){
        return finalDesRow;
    }

    public int getDesColumn () {
        return finalDesColumn;
    }

    public String getErrorMsg (){
        return strErrorMsg;
    }
}