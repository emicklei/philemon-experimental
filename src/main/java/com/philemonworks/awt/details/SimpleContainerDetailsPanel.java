package com.philemonworks.awt.details;

/**
 * Insert the type's description here. Creation date: (23-06-2000 9:15:40)
 * 
 * @author: Ernest Micklei
 */
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.AdjustmentEvent;
import java.util.Vector;

public class SimpleContainerDetailsPanel extends java.awt.Panel implements java.awt.event.ActionListener,
        java.awt.event.MouseListener, java.awt.event.AdjustmentListener {
    private java.awt.Scrollbar scrollbar = null;
    private java.awt.Button[] headers = null;
    private java.awt.Label[][] cells = null;
    public int rows = 8;
    public int columns = 1;
    public int rowHeight = 20;
    public int[] cellWidths = {};
    public int hgap = 2;
    public int vgap = 1;
    public java.awt.Color cellBackground = Color.white;
    public java.awt.Color cellForeground = Color.black;
    public java.awt.Color cellSelectionBackground = SystemColor.textHighlight;
    public java.awt.Color cellSelectionForeground = SystemColor.textHighlightText;
    public Vector items;
    protected transient java.beans.PropertyChangeSupport propertyChange;
    private int fieldSelectionIndex = -1;
    private int offset = 0;
    private int sortSigns[];
    // for sorting
    int a, b;

    /**
     * SimpleContainerDetailsPanel constructor comment.
     */
    public SimpleContainerDetailsPanel() {
        super();
        initialize();
    }
    /**
     * SimpleContainerDetailsPanel constructor comment.
     * 
     * @param layout
     *                java.awt.LayoutManager
     */
    public SimpleContainerDetailsPanel(java.awt.LayoutManager layout) {
        super(layout);
    }
    public void actionPerformed(java.awt.event.ActionEvent e) {

        for (int h = 0; h < headers.length; h++)
            if (e.getSource() == headers[h]) {
                this.sortItemsByColumnIndex(h);
            }
    }
    /**
     * The addPropertyChangeListener method was generated to support the
     * propertyChange field.
     */
    public synchronized void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        getPropertyChange().addPropertyChangeListener(listener);
    }
    public void adjustmentValueChanged(AdjustmentEvent e) {

        if (e.getValue() == offset)
            return;
        unhighlightIndex(fieldSelectionIndex - offset);
        offset = e.getValue();
        this.updateRows();
        highlightIndex(fieldSelectionIndex - offset);
    }
    private void buildHeaders() {

        java.awt.Button button;
        headers = new java.awt.Button[columns];
        for (int c = 0; c < columns; c++) {
            button = new java.awt.Button();
            button.setName(String.valueOf('h') + String.valueOf(c));
            button.setLabel("");
            headers[c] = button;
            button.addActionListener(this);
            this.add(button);
        }
    }
    private void buildRows() {

        java.awt.Label label;
        cells = new java.awt.Label[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                label = new java.awt.Label();
                label.setName(String.valueOf(r) + String.valueOf(c));
                label.setText("");
                label.setBackground(cellBackground);
                cells[r][c] = label;
                label.addMouseListener(this);
                this.add(label);
            }
        }
    }
    private void buildScrollbar() {

        scrollbar = new java.awt.Scrollbar();
        scrollbar.setName("Scrollbar");
        //	scrollbar.setBlockIncrement(rowHeight);
        //	scrollbar.setUnitIncrement(rowHeight);
        scrollbar.addAdjustmentListener(this);
        this.add(scrollbar);
    }
    /**
     * The firePropertyChange method was generated to support the propertyChange
     * field.
     */
    public void firePropertyChange(java.lang.String propertyName, java.lang.Object oldValue, java.lang.Object newValue) {
        getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
    }
    /**
     * Insert the method's description here. Creation date: (7/5/2000 11:30:14
     * PM)
     * 
     * @return int
     */
    public int getColumns() {
        return columns;
    }
    public Vector getItems() {

        return items;
    }
    /**
     * Accessor for the propertyChange field.
     */
    protected java.beans.PropertyChangeSupport getPropertyChange() {
        if (propertyChange == null) {
            propertyChange = new java.beans.PropertyChangeSupport(this);
        }
        ;
        return propertyChange;
    }
    /**
     * Insert the method's description here. Creation date: (7/5/2000 11:55:33
     * PM)
     * 
     * @return int
     */
    public int getRows() {
        return rows;
    }
    public Object getSelectedItem() {

        if (fieldSelectionIndex < 0)
            return null;
        else
            return items.elementAt(fieldSelectionIndex);
    }
    /**
     * Gets the selectionIndex property (int) value.
     * 
     * @return The selectionIndex property value.
     * @see #setSelectionIndex
     */
    public int getSelectionIndex() {
        return fieldSelectionIndex;
    }
    /**
     * Called whenever the part throws an exception.
     * 
     * @param exception
     *                java.lang.Throwable
     */
    private void handleException(java.lang.Throwable exception) {

        /* Uncomment the following lines to print uncaught exceptions to stdout */
        // System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        // exception.printStackTrace(System.out);
    }
    private void highlightIndex(int newIndex) {

        if (newIndex < 0)
            return;
        if (newIndex >= rows)
            return;
        for (int c = 0; c < columns; c++) {
            java.awt.Label newlabel = cells[newIndex][c];
            newlabel.setForeground(cellSelectionForeground);
            newlabel.setBackground(cellSelectionBackground);
        }
    }
    /**
     * Initialize the class.
     */
    /* WARNING: THIS METHOD WILL BE REGENERATED. */
    private void initialize() {
        try {
            // user code begin {1}
            // user code end
            setName("SimpleContainerDetailsPanel");
            setLayout(null);
            setSize(520, 240);
        } catch (java.lang.Throwable ivjExc) {
            handleException(ivjExc);
        }
        // user code begin {2}
        // user code end
    }
    /**
     * main entrypoint - starts the part when it is run as an application
     * 
     * @param args
     *                java.lang.String[]
     */
    public static void main(java.lang.String[] args) {
        try {
            java.awt.Frame frame = new java.awt.Frame();
            SimpleContainerDetailsPanel aSimpleContainerDetailsPanel;
            aSimpleContainerDetailsPanel = new SimpleContainerDetailsPanel();
            frame.add("Center", aSimpleContainerDetailsPanel);
            frame.setSize(aSimpleContainerDetailsPanel.getSize());
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                };
            });
            frame.setVisible(true);
        } catch (Throwable exception) {
            System.err.println("Exception occurred in main() of java.awt.Panel");
            exception.printStackTrace(System.out);
        }
    }
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }
    public void mouseExited(java.awt.event.MouseEvent e) {
    }
    public void mousePressed(java.awt.event.MouseEvent e) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < headers.length; c++) {
                java.awt.Label label = cells[r][c];
                if (e.getSource() == label) {
                    setSelectionIndex(r + offset);
                    return;
                }
            }
        }
    }
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }
    private void positionHeaders() {

        int x = hgap;
        int y = vgap;
        for (int c = 0; c < columns; c++) {
            headers[c].setBounds(x, y, cellWidths[c] + 2, rowHeight);
            x = x + cellWidths[c] + hgap;
        }
    }
    private void positionRows() {

        int x = hgap;
        int y = rowHeight + vgap + vgap;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                cells[r][c].setBounds(x, y, cellWidths[c], rowHeight);
                x = x + cellWidths[c] + hgap;
            }
            x = hgap;
            y = y + rowHeight + vgap;
        }
    }
    private void positionScrollbar() {

        int x = hgap;
        for (int c = 0; c < columns; c++) {
            x = x + cellWidths[c] + hgap;
        }
        int y = vgap + rowHeight + vgap;
        int h = rows * (vgap + rowHeight);
        scrollbar.setBounds(x, y, 15, h);
    }
    /**
     * The removePropertyChangeListener method was generated to support the
     * propertyChange field.
     */
    public synchronized void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        getPropertyChange().removePropertyChangeListener(listener);
    }
    public void setCellFont(java.awt.Font newFont) {

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                cells[r][c].setFont(newFont);
            }
        }
    }
    public void setCellWidths(int[] widths) {

        cellWidths = widths;
        this.positionHeaders();
        this.positionRows();
        this.positionScrollbar();
    }
    /**
     * Insert the method's description here. Creation date: (7/5/2000 11:30:14
     * PM)
     * 
     * @param newColumns
     *                int
     */
    public void setColumns(int newColumns) {
        columns = newColumns;
        // build components
        this.buildHeaders();
        this.buildRows();
        this.buildScrollbar();
        // default widths
        int[] widths = new int[columns];
        for (int i = 0; i < columns; i++)
            widths[i] = 50;
        this.setCellWidths(widths);
    }
    public void setHeaderFont(java.awt.Font newFont) {

        for (int h = 0; h < headers.length; h++)
            headers[h].setFont(newFont);
    }
    public void setHeaders(String[] headerTexts) {

        for (int h = 0; h < headers.length; h++)
            headers[h].setLabel(headerTexts[h]);
    }
    public void setItems(Vector objects) {

        setSelectionIndex(-1);
        scrollbar.setValues(0, rows, 0, objects.size());
        items = objects;
        this.updateRows();
        // reset sortorder to none
        sortSigns = new int[columns];
        for (int i = 0; i < columns; i++)
            sortSigns[i] = 0;
    }
    /**
     * Insert the method's description here. Creation date: (7/5/2000 11:55:33
     * PM)
     * 
     * @param newRows
     *                int
     */
    public void setRowHeight(int rh) {

        rowHeight = rh;
    }
    /**
     * Insert the method's description here. Creation date: (7/5/2000 11:55:33
     * PM)
     * 
     * @param newRows
     *                int
     */
    public void setRows(int newRows) {
        rows = newRows;
    }
    public void setSelectedItem(SimpleContainerDetails anItem) {

        int index = -1;
        if (anItem != null)
            index = items.indexOf(anItem);
        this.setSelectionIndex(index);
    }
    /**
     * Sets the selectionIndex property (int) value.
     * 
     * @param selectionIndex
     *                The new value for the property.
     * @see #getSelectionIndex
     */
    public void setSelectionIndex(int selectionIndex) {

        if (items == null)
            return;
        if (selectionIndex >= items.size())
            return;
        this.unhighlightIndex(fieldSelectionIndex - offset);
        if (selectionIndex >= 0) {
            int index;
            // recompute offset if selection out of range
            if (selectionIndex < offset) {
                offset = selectionIndex;
                index = 0;
                this.updateRows();
                scrollbar.setValue(offset);
            } else if (selectionIndex > (offset + rows - 1)) {
                offset = selectionIndex - rows + 1;
                index = rows - 1;
                this.updateRows();
                scrollbar.setValue(offset);
            } else {
                index = selectionIndex - offset;
            }
            this.highlightIndex(index);
        }

        int oldValue = fieldSelectionIndex;
        fieldSelectionIndex = selectionIndex;
        firePropertyChange("selectionIndex", new Integer(oldValue), new Integer(selectionIndex));
    }
    private void sort_partition(int p, int q, int k) {

        SimpleContainerDetails mid = (SimpleContainerDetails) items.elementAt((p + q) / 2);
        a = p;
        b = p;
        int c = q;
        while (b != c) {
            SimpleContainerDetails each = (SimpleContainerDetails) items.elementAt(b);
            int s = each.compareDetails(k, mid, true);
            s = s * sortSigns[k];
            if (s == 0)
                b++;
            else if (s > 0) {
                sort_swap(b, c - 1);
                c--;
            } else if (s < 0) {
                sort_swap(b, a);
                a++;
                b++;
            }
        }
    }
    private void sort_quicksort(int p, int q, int k) {

        if (q - p >= 2) {
            sort_partition(p, q, k);
            sort_quicksort(p, a, k);
            sort_quicksort(b, q, k);
        }
    }
    private void sort_swap(int i, int j) {

        Object h = items.elementAt(i);
        items.setElementAt(items.elementAt(j), i);
        items.setElementAt(h, j);
    }
    public void sortItemsByColumnIndex(int column) {

        SimpleContainerDetails oldSelection = (SimpleContainerDetails) getSelectedItem();
        // toggle sorting
        if (sortSigns[column] == 0)
            sortSigns[column] = -1;
        else
            sortSigns[column] = sortSigns[column] * -1;
        // reset other sort orders
        for (int i = 0; i < columns; i++)
            if (i != column)
                sortSigns[i] = 0;
        // do the sort
        sort_quicksort(0, items.size(), column);
        // show them
        updateRows();
        setSelectedItem(oldSelection);
    }
    private void unhighlightIndex(int oldIndex) {

        if (oldIndex < 0)
            return;
        if (oldIndex >= rows)
            return;
        for (int c = 0; c < columns; c++) {
            java.awt.Label oldlabel = cells[oldIndex][c];
            oldlabel.setForeground(cellForeground);
            oldlabel.setBackground(cellBackground);
        }
    }
    private void updateRows() {

        String[] details = null;

        for (int r = 0; r < rows; r++) {
            if (r < items.size()) {
                details = ((SimpleContainerDetails) items.elementAt(r + offset)).containerDetailsFor(this);
            }
            for (int c = 0; c < headers.length; c++) {
                java.awt.Label label = cells[r][c];
                if (r < items.size())
                    label.setText(" " + details[c]);
                else
                    label.setText("");
            }
        }
    }
}