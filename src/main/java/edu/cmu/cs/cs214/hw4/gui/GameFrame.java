package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.GameActionListener;
import edu.cmu.cs.cs214.hw4.core.Map;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.XYCoordinate;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * the gameframe of this game
 */
public class GameFrame extends JFrame implements GameActionListener{
    private Game g;
    private JLabel tileShower;
    private ArrayList<PlayerRow> playerRows;
    private HashMap<XYCoordinate, JButton> currentMap;
    private HashMap<Tile, JButton> tileToJB;
    private HashMap<Segment, TileInfo> hasFollowerSegments;
    private int[] horizantoBorders; //first one is on the right side, second is on the left
    private int[] verticoBorders; //first one is on the top side, second is on the bottom
    private JScrollPane scrollPane;
    private JPanel controlPanel;
    private JLabel instructionArea;
    private JButton rotateBtn;
    private Tile currentTile;
    private JButton redrawBtn;
    private Segment segmentWithFollower;
    private JLabel consoleArea;
    private BufferedImage originalTileImage;
    private BufferedImage tileImage; //the imgae of new tile, won't include follower
    private BufferedImage tileImageWithFollower; //the imgae of new tile, include follower
    private HashMap<Player, JTextArea> playerScoreTAreas;
    private HashMap<Player, Color> playerColors;
    private ArrayList<Player> playerList;
    private ArrayList<JButton> controlBtn = new ArrayList<JButton>();
    private Player currentPlayer;
    private String filename = "Carcassonne.png";
    private String instruction
            = "<html>" +
            "    Instruction "  + "<br />"
            +  " 1. rotate the tile" + "<br />"
            + "  2. choose a direction to place follower" + "<br />"
            + "  3. press PlaceF button"
            + "  4. regret? press Rem button"
            + "</html>";
    private String invalidFollowerPlacement
            = "<html>" +
            "This is an invalid follower placement. Please select other places"
            + "</html>";

    /**
     * the constructor of gameframe
     * @param g the game that this game depends on
     */
    public GameFrame(Game g){
        this.g = g;
        g.subscribeGameActionListener(this);
        playerRows = new ArrayList<PlayerRow>();
        currentMap = new HashMap<XYCoordinate, JButton>();
        tileToJB = new HashMap<Tile, JButton> ();
        hasFollowerSegments = new HashMap<Segment, TileInfo>();
        playerScoreTAreas = new HashMap<Player, JTextArea>();
        horizantoBorders = new int[2];
        verticoBorders = new int[2];
        horizantoBorders[0] = 11;
        horizantoBorders[1] = -12;
        verticoBorders[0] = 5;
        verticoBorders[1] = -6;
        rotateBtn = new JButton();
        currentTile = null;
        tileImage = null;
        tileImageWithFollower = null;
        playerList = g.getPlayerList();
        playerColors = new HashMap<Player, Color>();
        setPlayerColor();
        setTitle("GameBoard");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.add(new GamePanel());
        g.gameStart();
    }

    private void setPlayerColor (){
        int counter = 1;
        for (Player p : playerList){
            switch (counter){
                case 1:
                    playerColors.put(p, Color.BLUE);
                    break;
                case 2:
                    playerColors.put(p, Color.RED);
                    break;
                case 3:
                    playerColors.put(p, Color.GREEN);
                    break;
                case 4:
                    playerColors.put(p, Color.BLACK);
                    break;
                case 5:
                    playerColors.put(p, Color.YELLOW);
                    break;
                default:
                    throw new IllegalArgumentException("player number out of bound");
            }
            counter++;
        }
    }

    /**
     * the game panel. contains four main panels
     * MapScrollPanel
     * ScorePanel
     * ConsolePanel
     * TilePanel
     */
    private class GamePanel extends JPanel {
        GamePanel() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            this.add(new MapScrollPanel(), gbc);
            gbc.gridx++;
            ArrayList<Player> playerList = g.getPlayerList();
            this.add(new ScorePanel(playerList), gbc);
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            this.add(new ConsolePanel(), gbc);
            gbc.gridwidth = 1;
            gbc.gridx = 1;
            gbc.gridy = 1;
            this.add(new TilePanel(), gbc);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1200, 750);
        }
    }

    /**
     * Tile panel. show the current tile that the current player drawn
     */
    private final class TilePanel extends JPanel{
        private TilePanel(){
            tileShower = new JLabel();
            add(tileShower);
            tileShower.setBackground(new Color(193,225,148));
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 100);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private void scrollToCenter() {
        Rectangle bounds = scrollPane.getViewport().getViewRect();
        Dimension size = scrollPane.getViewport().getViewSize();
        int x = (size.width - bounds.width) / 2 - 45*12;
        int y = (size.height - bounds.height) / 2 - 45*6;
        scrollPane.getViewport().setViewPosition(new Point(x, y));
    }

    /**
     * Show the map of the game with all the tiles on it
     * it's a scrollable panel
     * it will expand when tiles expand to the borders
     */
    private final class MapScrollPanel extends JPanel {
        private MapScrollPanel (){
            scrollPane = new JScrollPane(new MapPanel()) {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(1050, 600);
                }
            };
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);
            scrollToCenter();
        }
    }

    private void checkBorder(JPanel jPanel, GridBagConstraints mapGBC, int x, int y){
        if (x == horizantoBorders[0]){
            int temp = horizantoBorders[0];
            horizantoBorders[0] += 12;
            buildMap(jPanel, mapGBC, temp + 1, horizantoBorders[0], verticoBorders[1], verticoBorders[0]);
        }else if (x == horizantoBorders[1]){
            int temp = horizantoBorders[1];
            horizantoBorders[1] -= 12;
            buildMap(jPanel, mapGBC, horizantoBorders[1], temp - 1, verticoBorders[1], verticoBorders[0]);
        }else if (y == verticoBorders[0]){
            int temp = verticoBorders[0];
            verticoBorders[0] += 6;
            buildMap(jPanel, mapGBC, horizantoBorders[1], horizantoBorders[0], temp + 1, verticoBorders[0]);
        }else if (y == verticoBorders[1]){
            int temp = verticoBorders[1];
            verticoBorders[1] -= 6;
            buildMap(jPanel, mapGBC, horizantoBorders[1], horizantoBorders[0], verticoBorders[1], temp - 1);
        }else {
            return;
        }
    }

    private void buildMap (JPanel jpanel, GridBagConstraints mapGBC, int xstart, int xend, int ystart, int yend){
        for(int i = ystart; i < yend + 1; i++){
            for (int j = xstart; j < xend + 1; j++){
                int y = i;
                int x = j;
                XYCoordinate tempXY = new XYCoordinate(x, y);
                JButton tempJB = new JButton();
                if (x == 0 && y == 0){
                    tempJB.setBackground(new Color(240,128,128));
                }
                tempJB.addActionListener(e->{
                    Tile tempTile = currentTile;
                    BufferedImage tempImg = tileImage;
                    if(g.placeNewTile(x, y, tempTile)){
                        consoleArea.setText("");
                        tileToJB.put(tempTile,tempJB);
                        tempImg = GameFrame.this.resize(tempImg, 45, 45);
                        ImageIcon icon = new ImageIcon(tempImg);
                        tempJB.setIcon(icon);
                        checkFollowerRemoved();
                        checkBorder(jpanel, mapGBC, x, y);
                    }else {
                        consoleArea.setText("invalid tile placement");
                    }
                });
                tempJB.setPreferredSize(new Dimension(45, 45));
                tempJB.setMinimumSize(new Dimension(45, 45));
                tempJB.setMaximumSize(new Dimension(45, 45));
                currentMap.put(tempXY, tempJB);
                mapGBC.gridx = x + 72;
                mapGBC.gridy = 72 - y;
                jpanel.add(tempJB, mapGBC);
            }
        }
    }

    /**
     * Show the map of the game with all the tiles on it
     * contain by the scrollable map panel
     */
    private final class MapPanel extends JPanel{
        private MapPanel(){
            setLayout(new GridBagLayout());
            GridBagConstraints mapGBC = new GridBagConstraints();
            buildMap(this, mapGBC,-12, 11, -6, 5);
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(3250, 3250);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }

    }

    /**
     * console panel that show error messages and contain the control panel
     */
    private class ConsolePanel extends JPanel {
        ConsolePanel(){
            setBackground(new Color(165, 225, 181));
            setLayout(new GridLayout(1, 5));
            instructionArea = new JLabel(instruction);
            instructionArea.setForeground(Color.WHITE);
            this.add(instructionArea);
            consoleArea = new JLabel();
            this.add(consoleArea);

            rotateBtn = new JButton("Rotate tile");
            rotateBtn.setPreferredSize(new Dimension(50, 100));
            rotateBtn.setMaximumSize(new Dimension(50, 100));
            rotateBtn.setMinimumSize(new Dimension(50, 100));
            rotateBtn.addActionListener(e->{
                currentTile.rotateTile("clockwise");
                drawRotateClockwise();
            });
            this.add(rotateBtn);

            redrawBtn = new JButton("Redraw tile");
            redrawBtn.setPreferredSize(new Dimension(50, 100));
            redrawBtn.setMaximumSize(new Dimension(50, 100));
            redrawBtn.setMinimumSize(new Dimension(50, 100));
            redrawBtn.addActionListener(e->{
                currentTile = g.drawATile();
                segmentWithFollower = null;
                if(currentTile == null){
                }else {
                    drawNewTilePanel();
                    setConrolPanelEnable(true);
                }
            });
            this.add(redrawBtn);

            controlPanel = new ControlPanel();
            this.add(controlPanel);
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1100, 100);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }
    }

    /**
     * control panel that contains all buttons that can control the current tile
     * like place followers and rotate tile
     */
    private class ControlPanel extends JPanel{
        private Direction placement = null;
        ControlPanel(){
            setLayout(new GridLayout(5, 3));
            setBackground(new Color(165, 225, 181));
            //north side
            JButton nwJBtn = new JButton("NNW");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(30, 10, 10, playerColors.get(currentPlayer));
                placement = Direction.NNW;
            });
            this.add(nwJBtn );
            nwJBtn = new JButton("N");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(50, 10, 10, playerColors.get(currentPlayer));
                placement = Direction.N;
            });
            this.add(nwJBtn );
            nwJBtn = new JButton("NNE");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(70, 10, 10, playerColors.get(currentPlayer));
                placement = Direction.NNE;
            });
            this.add(nwJBtn );

            //West side
            nwJBtn = new JButton("WNW");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(10, 30, 10, playerColors.get(currentPlayer));
                placement = Direction.WNW;
            });
            this.add(nwJBtn );
            nwJBtn = new JButton("W");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(10, 50, 10, playerColors.get(currentPlayer));
                placement = Direction.W;
            });
            this.add(nwJBtn );
            nwJBtn = new JButton("WSW");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(10, 70, 10, playerColors.get(currentPlayer));
                placement = Direction.WSW;
            });
            this.add(nwJBtn );

            //South side
            nwJBtn = new JButton("SSW");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(30, 90, 10, playerColors.get(currentPlayer));
                placement = Direction.SSW;
            });
            this.add(nwJBtn );
            nwJBtn = new JButton("S");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(50, 90, 10, playerColors.get(currentPlayer));
                placement = Direction.S;
            });
            this.add(nwJBtn );
            nwJBtn = new JButton("SSE");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(70, 90, 10, playerColors.get(currentPlayer));
                placement = Direction.SSE;
            });
            this.add(nwJBtn );

            //East side
            nwJBtn = new JButton("ESE");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(90, 70, 10, playerColors.get(currentPlayer));
                placement = Direction.ESE;
            });
            this.add(nwJBtn );
            nwJBtn = new JButton("E");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(90, 50, 10, playerColors.get(currentPlayer));
                placement = Direction.E;
            });
            this.add(nwJBtn );
            nwJBtn = new JButton("ENE");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(90, 30, 10, playerColors.get(currentPlayer));
                placement = Direction.ENE;
            });
            this.add(nwJBtn );
            //Cloister
            nwJBtn = new JButton("Cloister");
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                drawFollowerCircle(50, 50, 10, playerColors.get(currentPlayer));
                placement = Direction.Cloister;
            });
            this.add(nwJBtn);

            //Confirm button
            nwJBtn = new JButton("PlaceF");
            nwJBtn.setBackground(new Color(240,128,128));
            nwJBtn.setOpaque(true);
            controlBtn.add(nwJBtn);
            nwJBtn.addActionListener(e->{
                consoleArea.setText("");
                if (!checkValidFollowPlacement(placement)){
                    //invalid follower placement
                    consoleArea.setText(invalidFollowerPlacement);
                    return;
                }
                if (placeFollower(placement)){
                    tileImage = tileImageWithFollower;
                    setConrolPanelEnable(false);
                }else {
                    consoleArea.setText("no follower available for this player");
                }

            });
            this.add(nwJBtn);

            //remove follower
            nwJBtn = new JButton("RemoveF");
            nwJBtn.addActionListener(e->{
                if (segmentWithFollower.getFollower() != null){
                    currentPlayer.getFollowerBack(segmentWithFollower.getFollower());
                    segmentWithFollower.removeFollower();
                    hasFollowerSegments.remove(segmentWithFollower);
                    consoleArea.setText("");
                    setConrolPanelEnable(true);
                    tileImage = originalTileImage;
                    ImageIcon icon = new ImageIcon(tileImage);
                    tileShower.setIcon(icon);
                }
            });
            this.add(nwJBtn);
        }
    }

    private void setConrolPanelEnable (boolean setBoolean){
        for (JButton jbtn : controlBtn){
            jbtn.setEnabled(setBoolean);
        }
        rotateBtn.setEnabled(setBoolean);
    }

    private boolean placeFollower(Direction placement){
        Segment targetS = null;
        if (placement==Direction.NNW){
            targetS = currentTile.getNorthSegments().get(0);
        }else if (placement==Direction.N){
            if (currentTile.getNorthSegments().size() == 1){
                targetS = currentTile.getNorthSegments().get(0);
            }else {
                targetS = currentTile.getNorthSegments().get(1);
            }
        }else if (placement==Direction.NNE){
            if (currentTile.getNorthSegments().size() == 1){
                targetS = currentTile.getNorthSegments().get(0);
            }else {
                targetS = currentTile.getNorthSegments().get(2);
            }
        }else if (placement==Direction.WSW){
            targetS = currentTile.getWestSegments().get(0);
        }else if (placement==Direction.W){
            if (currentTile.getWestSegments().size() == 1){
                targetS = currentTile.getWestSegments().get(0);
            }else {
                targetS = currentTile.getWestSegments().get(1);
            }
        }else if (placement==Direction.WNW){
            if (currentTile.getWestSegments().size() == 1){
                targetS = currentTile.getWestSegments().get(0);
            }else {
                targetS = currentTile.getWestSegments().get(2);
            }
        }else if (placement==Direction.SSE){
            targetS = currentTile.getSouthSegments().get(0);
        }else if (placement==Direction.S){
            if (currentTile.getSouthSegments().size() == 1){
                targetS = currentTile.getSouthSegments().get(0);
            }else {
                targetS = currentTile.getSouthSegments().get(1);
            }
        }else if (placement==Direction.SSW){
            if (currentTile.getSouthSegments().size() == 1){
                targetS = currentTile.getSouthSegments().get(0);
            }else {
                targetS = currentTile.getSouthSegments().get(2);
            }
        }else if (placement==Direction.ENE){
            targetS = currentTile.getEastSegments().get(0);
        }else if (placement==Direction.E){
            if (currentTile.getEastSegments().size() == 1){
                targetS = currentTile.getEastSegments().get(0);
            }else {
                targetS = currentTile.getEastSegments().get(1);
            }
        }else if (placement==Direction.ESE){
            if (currentTile.getEastSegments().size() == 1){
                targetS = currentTile.getEastSegments().get(0);
            }else {
                targetS = currentTile.getEastSegments().get(2);
            }
        }else if (placement==Direction.Cloister){
            targetS = currentTile.getCloisterSegment();
        }
        if (g.placeFollower(targetS)){

            TileInfo tinfo = new TileInfo(currentTile, resize(originalTileImage, 45, 45));
            hasFollowerSegments.put(targetS, tinfo);
            segmentWithFollower = targetS;
            return true;
        }else {
            return false;
        }
    }

    private boolean checkValidFollowPlacement(Direction placement){
        if (placement==Direction.Cloister && (currentTile.getCloisterSegment() == null)){
            return false;
        }else {
            return true;
        }
    }

    /**
     * show the score of the players
     */
    private class ScorePanel extends JPanel{
        ScorePanel (ArrayList<Player> playerList) {
            setBackground(new Color(137,225,218));
            for (int i = 0 ; i < playerList.size() ; i++) {
                PlayerRow tempPR = new PlayerRow(playerList.get(i));
                playerRows.add(tempPR);
                this.add(tempPR);
            }
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 650);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }
    }

    /**
     * in score panel, show a single players name and score
     */
    private class PlayerRow extends JPanel {
        private JLabel name;
        private JTextArea score;
        private JLabel points;
        PlayerRow(Player player) {
            name = new JLabel(player.getName());
            score = new JTextArea("0");
            playerScoreTAreas.put(player, score);
            points = new JLabel("pts");
            this.add(name);
            this.add(score);
            this.add(points);
        }
    }

    /**
     * draw new tile to the tile panel. set tileImage
     */
    private void drawNewTilePanel(){
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            int tileID = currentTile.getTileID()%24;
            tileImage = image.getSubimage((tileID%6)*90, (tileID/6)*90, 90, 90);
            originalTileImage = tileImage;
            ImageIcon icon = new ImageIcon(tileImage);
            tileShower.setIcon(icon);
        }catch (IOException e){

        }
    }

    /**
     * rotate new tile one time clockwise
     * and draw the new tile to tile panel
     */
    private void drawRotateClockwise() {
        int w = tileImage.getWidth();
        int h = tileImage.getHeight();

        AffineTransform at = AffineTransform.getQuadrantRotateInstance(1, w / 2.0, h / 2.0);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

        BufferedImage dest = new BufferedImage(w, h, tileImage.getType());
        op.filter(tileImage, dest);
        tileImage = dest;
        originalTileImage = tileImage;
        ImageIcon icon = new ImageIcon(tileImage);
        tileShower.setIcon(icon);
    }

    private void drawFollowerCircle(int x, int y, int radius, Color c) {
        BufferedImage dest = new BufferedImage(tileImage.getWidth(), tileImage.getHeight(), tileImage.getType());

        Graphics2D g = (Graphics2D) dest.getGraphics();
        g.drawImage(tileImage, 0, 0, null);
        g.setColor(c);
        g.fillOval(x - radius, y - radius, radius, radius);
        g.dispose();
        tileImageWithFollower = dest;
        ImageIcon icon = new ImageIcon(dest);
        tileShower.setIcon(icon);
    }

    private void checkFollowerRemoved (){
        for (java.util.Map.Entry<Segment, TileInfo> entry : hasFollowerSegments.entrySet()) {
            Segment s = entry.getKey();
            TileInfo value = entry.getValue();
            if(s.getFollower() == null){
                ImageIcon icon = new ImageIcon(value.getTImage());
                tileToJB.get(value.getT()).setIcon(icon);
            }
        }
    }


    private void setCurrentMapEnable (boolean setEnable){
        for (JButton jb : currentMap.values()){
            jb.setEnabled(setEnable);
        }
    }


    /**
     * called every round
     * provide the scores of all players
     * @param playerScoreList the new score
     */
    @Override
    public void scoreChanged(HashMap<Player, Integer> playerScoreList){
        for (Player p : playerScoreList.keySet()){
            playerScoreTAreas.get(p).setText(Integer.toString(playerScoreList.get(p)));
        }
    }

    /**
     * play has changed
     * change currentPlayer
     * draw new tile
     * @param p the next player
     */
    @Override
    public void playerChanged(Player p){
        currentPlayer = p;
        currentTile = g.drawATile();
        segmentWithFollower = null;
        if(currentTile == null){
            setCurrentMapEnable(false);
        }else {
            drawNewTilePanel();
            setConrolPanelEnable(true);
        }

    }

    /**
     * final score came out
     * set all players score
     * show final winners
     * @param finalScores map of player and he's final score
     */
    @Override
    public void finalScore(ArrayList<Player> winners, HashMap<Player, Integer> finalScores){
        for (Player p : finalScores.keySet()){
            playerScoreTAreas.get(p).setText(Integer.toString(finalScores.get(p)));
        }
        instructionArea.setBackground(new Color(240,128,128));
        String winningMessage = "winner: ";
        for (Player p : winners){
            winningMessage += p.getName() + " ";
        }
        instructionArea.setText(winningMessage);
    }

}
