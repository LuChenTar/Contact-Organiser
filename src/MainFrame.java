/**
 * Created by luchen on 15/03/15.
 *
 * Description: set up the main frame
 **/

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

class BackImage extends JComponent {
    Image i;

    //    creating constructer
    public BackImage(Image i) {
        this.i = i;
    }

    @Override
    public void paintComponent(Graphics g) {
        //drawing image using drawImage method
        g.drawImage(i, 0, 0, getWidth(), getHeight(), null);
    }
}


public class MainFrame extends JFrame {
    int height = 680;
    int width = 480;

    //variable declaration
    private String picpath = "images/background.jpg";
    private JLabel lblAllContactInformation = new JLabel();
    private JLabel instrutions = new JLabel();
    private JLabel authorship = new JLabel();
    private JLabel sortme = new JLabel();
    private JLabel searchme = new JLabel();
    private JButton btnNewContact = new JButton();
    private JButton btnUpdate = new JButton();
    private JButton btnDelete = new JButton();
    private JButton btnDetail = new JButton();
    private JButton btnSearch = new JButton();
    private JButton yeah = new JButton();
    private JTextField searchbar = new JTextField();
    private JMenuBar menu = new JMenuBar();
    private JMenu preference = new JMenu();
    private JMenu fontSize = new JMenu();
    private JMenu about = new JMenu();
    private JMenu theme = new JMenu();
    private JMenuItem large = new JMenuItem();
    private JMenuItem def = new JMenuItem();
    private JMenuItem small = new JMenuItem();
    private JMenuItem sunrise = new JMenuItem();
    private JMenuItem sunset = new JMenuItem();
    private JMenuItem deftheme = new JMenuItem();
    private JMenuItem author = new JMenuItem();
    private JMenuItem help = new JMenuItem();
    private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JScrollPane scrollPane = new JScrollPane();
    private int titleFontSize = 21;
    private int contentFontSize = 13;
    private int tableFontSize = 15;
    private boolean isHelp = false;
    private boolean isAuthor = false;
    private Color renderColor1 = new Color(184, 202, 215);
    private Color renderColor2 = new Color(246, 204, 153);
    //end of declaration

    private void setUpTheme() throws IOException {
        BufferedImage bf = ImageIO.read(new File(picpath));
        BackImage currentPanel = new BackImage(bf);
        this.setContentPane(currentPanel);
        this.add(lblAllContactInformation);
        this.add(btnNewContact);
        this.add(scrollPane);
        this.add(btnUpdate);
        this.add(btnDelete);
        this.add(btnDetail);
        this.add(btnSearch);
        this.add(searchbar);
        this.add(searchme);
        this.add(yeah);
        this.add(sortme);
        //reload the main frame
        this.invalidate();
        this.validate();
        this.repaint();
    }


    public MainFrame() throws IOException {
        setTitle("Organiser Plus");
        setSize(width, height);
        createMainMenuBar();
        setUpTheme();
        setVisible(true);

        //setup label, table and button
        lblAllContactInformation = new JLabel("All Contact Information View");
        lblAllContactInformation.setBounds(40, 20, 400, 20);
        lblAllContactInformation.setForeground(Color.WHITE);
        this.add(lblAllContactInformation);


        btnNewContact = new JButton("New Contact");
        btnNewContact.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewContactFrame NewContactframe = new NewContactFrame();
            }
        });
        btnNewContact.setBounds(330, 50, 150, 30);
        this.add(btnNewContact);

        //show instruction to user to sort
        sortme = new JLabel("Press label and SORT me!");
        sortme.setBounds(120, 70, 230, 30);
        sortme.setVisible(false);
        this.add(sortme);
        //create a invisible button to restrain the area showing the sort when mouse hovers
        yeah = new JButton();
        yeah.setBounds(15, 80, 450, 23);
        yeah.setOpaque(false);
        yeah.setContentAreaFilled(false);
        yeah.setBorderPainted(false);
        yeah.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sortme.setVisible(true);
            }


            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sortme.setVisible(false);
            }
        });
        this.add(yeah);

        //setup the update button
        btnUpdate = new JButton("Update");
        btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {///lack of updating method
                try {
                    btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    updateTable();
                } finally {
                    btnUpdate.setCursor(Cursor.getDefaultCursor());
                }
            }
        });
        btnUpdate.setBounds(60, 480, 90, 30);
        this.add(btnUpdate);

        //setup the update button
        btnDelete = new JButton("Delete");
        btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    //access and modify the model data through the view data
                    System.out.println("I am deleting" + table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString());
                    deleteInfo(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString(),
                            table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 1).toString(),
                            table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 2).toString(),
                            table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 3).toString());
                }
            }
        });
        btnDelete.setBounds(200, 480, 90, 30);
        this.add(btnDelete);

        //setup the update button
        btnDetail = new JButton("Details");
        btnDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                } else {
                    DataTXT service = new DataTXT();
                    List<Person> dataLis = service.load();
                    NewContactFrame d = new NewContactFrame();
                    //access and modify the model data through the view data
                    System.out.println("Prepare to show details" + table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString());
                    for (Person p : dataLis) {
                        //problem: after sorting the info stays the same
                        if ((table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString()).equalsIgnoreCase(p.firstName) &&
                                (table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 1).toString()).equalsIgnoreCase(p.middleName) &&
                                (table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 2).toString()).equalsIgnoreCase(p.lastName) &&
                                (table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 3).toString()).equalsIgnoreCase(p.phoneNum)) {
                            System.out.println("match found");
                            d.showSelectedDetails(p);
                            break;
                        }
                    }
                    searchbar.setText("");
                }
            }
        });
        btnDetail.setBounds(340, 480, 90, 30);
        this.add(btnDetail);


        String[] columnNames = {"First Name", "Middle name",
                "Last Name",
                "Phone Number"};

        Object[][] data = {
        };

        table = new JTable() {
            //render color
            public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);
                if (data % 2 == 0)
                    c.setBackground(renderColor1);
                else
                    c.setBackground(renderColor2);
                return c;
            }
        };
        tableModel = new DefaultTableModel(data, columnNames) {
            //unable editting
            public boolean isCellEditable(int data, int columns) {
                return false;
            }

            //sort the name by column name
            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setModel(tableModel);
        //activate the table sorter
        table.setRowSorter(rowSorter);

        //Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(table);
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        table.setPreferredScrollableViewportSize(new Dimension(450, 350));

        scrollPane.setBounds(15, 100, 450, 350);
        this.add(scrollPane);

        //setup search bar
        //when searching is finished, the form needs to be reloaded
        //which means filter has no keyword
        btnSearch = new JButton("Reload");
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.setBounds(380, 530, 90, 30);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchbar.setText("");
                updateTable();
            }
        });
        btnSearch.setVisible(false);
        this.add(btnSearch);

        //search label
        searchme = new JLabel("Search");
        searchme.setBounds(380, 530, 90, 30);
        searchme.setVisible(true);
        this.add(searchme);

        searchbar = new JTextField();
        searchbar.setColumns(10);
        searchbar.setBounds(25, 530, 350, 30);
        //create a filter from search bar input
        searchbar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchbar.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
                System.out.println("I am searching for" + text);
                //update button is messing the form
                //hide the update button
                if (!text.isEmpty()) {
                    btnUpdate.setVisible(false);
                    btnSearch.setVisible(true);
                    searchme.setVisible(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchbar.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
                System.out.println("I am removing" + text);
                //when searching is done
                //show the update button, reset the interface to normal
                if (text.isEmpty()) {
                    btnUpdate.setVisible(true);
                    btnSearch.setVisible(false);
                    searchme.setVisible(true);
                    updateTable();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not implemented");
            }
        });
        this.add(searchbar);

        setUpFontSize();
        populateTable();
    }


    //customise the font size
    private void setUpFontSize() {
        lblAllContactInformation.setFont(new Font("Courier", Font.PLAIN, titleFontSize));
        btnNewContact.setFont(new Font("Courier", Font.PLAIN, contentFontSize));
        btnUpdate.setFont(new Font("Courier", Font.PLAIN, contentFontSize));
        btnDelete.setFont(new Font("Courier", Font.PLAIN, contentFontSize));
        btnDetail.setFont(new Font("Courier", Font.PLAIN, contentFontSize));
        btnSearch.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        table.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        menu.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        preference.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        fontSize.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        about.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        theme.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        large.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        def.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        small.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        author.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        help.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        sortme.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        sortme.setForeground(Color.RED);
        searchme.setFont(new Font("Courier", Font.PLAIN, tableFontSize));
        searchme.setForeground(Color.WHITE);
        if (isHelp) {
            instrutions.setFont(new Font("Courier", Font.PLAIN, contentFontSize));
        }
        if (isAuthor) {
            authorship.setFont(new Font("Courier", Font.PLAIN, contentFontSize));
        }
    }


    //set up the main navigation menu
    public void createMainMenuBar() {
        menu = new JMenuBar();
        //preference setup
        preference = new JMenu("Preferences");
        //theme changing : 1.background 2. table render color
        theme = new JMenu("Theme");
        sunrise = new JMenuItem("Sunrise");
        sunrise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderColor1 = new Color(160, 191, 171);
                renderColor2 = new Color(227, 197, 178);
                picpath = "images/sunrise.jpg";
                try {
                    setUpTheme();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        theme.add(sunrise);

        //theme changing 2
        sunset = new JMenuItem("Sunset");
        sunset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderColor1 = new Color(254, 134, 131);
                renderColor2 = new Color(251, 209, 150);
                picpath = "images/sunset.jpg";
                try {
                    setUpTheme();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        theme.add(sunset);
//      theme changing 3
        deftheme = new JMenuItem("Default");
        deftheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderColor1 = new Color(184, 202, 215);
                renderColor2 = new Color(246, 204, 153);
                picpath = "images/background.jpg";
                try {
                    setUpTheme();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        theme.add(deftheme);

        //font size changing
        fontSize = new JMenu("Font Size");

        large = new JMenuItem("Large");
        large.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleFontSize = 24;
                contentFontSize = 15;
                tableFontSize = 17;
                setUpFontSize();
            }
        });
        fontSize.add(large);

        small = new JMenuItem("Small");
        small.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentFontSize = 11;
                titleFontSize = 18;
                tableFontSize = 13;
                setUpFontSize();
            }
        });
        fontSize.add(small);

        def = new JMenuItem("Default");
        def.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleFontSize = 21;
                contentFontSize = 13;
                tableFontSize = 15;
                setUpFontSize();
            }
        });
        fontSize.add(def);

        preference.add(theme);
        preference.add(fontSize);
        menu.add(preference);
//      authorship and user instructions display
        about = new JMenu("About");
        author = new JMenuItem("Author");
        author.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame author = new JFrame("Oranizer Plus - Author");
                author.setSize(350, 220);
                authorship = new JLabel("<html> Author: Lu Chen<br>" +
                        "UID: u5486612<br>" +
                        "Thanks for using!");
                isAuthor = true;
                setUpFontSize();
                authorship.setBorder(new EmptyBorder(0, 110, 0, 0));
                author.add(authorship, BorderLayout.CENTER);

                JButton close = new JButton("Start Using!");
                close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        author.dispose();
                    }
                });
                author.add(close, BorderLayout.AFTER_LAST_LINE);
                author.setVisible(true);
            }
        });
        about.add(author);

        //instruction window set up
        help = new JMenuItem("Help");
        help.addActionListener(new ActionListener() {
            //set up the instruction windows
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame help = new JFrame("Organizer Plus - Help");
                help.setSize(350, 280);
                //use html label to input multilines text
                instrutions = new JLabel("<html>1. Add new contact information: <br>"
                        + "Press <font color='green'>New Contact</font><br>"
                        + "2. Edit/View detailed information:<br>"
                        + "Press <font color='green'>Details</font> and start editting!<br>"
                        + "3. Delete unwanted information:<br>"
                        + "Press <font color='green'>Delete!</font><br>"
                        + "4. Change <font color='green'>Preference</font> through main menu<br>"
                        + "5. Press the table column label to sort the form<br>"
                        + "6. Searching contacts through bar!<br>"
                        + "* When searching is done, press <font color='red'>Reload!</font><br>"
                        + "* Do not forget press<font color='red'> Update</font> after saving!</html>"
                );
                isHelp = true;
                setUpFontSize();
                instrutions.setBorder(new EmptyBorder(0, 20, 0, 0));
                help.add(instrutions, BorderLayout.CENTER);

                JButton close = new JButton("Start Using!");
                close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        help.dispose();
                    }
                });
                help.add(close, BorderLayout.AFTER_LAST_LINE);
                help.setVisible(true);
            }
        });
        about.add(help);
        menu.add(about);

        this.setJMenuBar(menu);
    }

    //populate the table when program first run
    private void populateTable() {
        try {
            DataTXT service = new DataTXT();
            List<Person> dataArr = service.load();
            for (Person p : dataArr) {
                tableModel.addRow(new Object[]{p.firstName, p.middleName, p.lastName, p.phoneNum});
            }
            System.out.println("Done populating");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //update the table after save new contact information
    public void updateTable() {
        try {
            DataTXT service = new DataTXT();
            List<Person> dataArr = service.load();
            System.out.println("I am the current amount of information " + dataArr.size());
            //delete the record from bottom to top to keep the order
            for (int i = table.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            System.out.println("Done erasing the old form");
            for (Person p : dataArr) {
                tableModel.addRow(new Object[]{p.firstName, p.middleName, p.lastName, p.phoneNum});
            }
            System.out.println("Done updating");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //delete info from table
    public void deleteInfo(String firstName, String middleName, String lastName, String phoneNum) {
        DataTXT service = new DataTXT();
        if (table.getSelectedRow() == -1) {
        } else {
            //remove data from database first to avoid index error
            //if remove successfully, then remove the data from model
            if (service.delete(firstName, middleName, lastName, phoneNum)) {
                tableModel.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
                System.out.println("The info selected is deleted");
            }
        }
    }
}
