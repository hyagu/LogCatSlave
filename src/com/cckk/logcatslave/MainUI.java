package com.cckk.logcatslave;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
//import org.eclipse.swt.widgets.MessageBox;

public class MainUI {
	private static MainUI mainUI;
	private static Display mDisplay;
	private Label mStatusLine;
	
	private MainUI(){
	}
	
	public static MainUI getInstance(){
		if (mainUI == null){
			mainUI = new MainUI();
		}
		return mainUI;
	}

	public void displayUI() {
	    Display.setAppName("LogCatSlave");
	    mDisplay = Display.getDefault();
	    Shell shell = new Shell(mDisplay, SWT.SHELL_TRIM);
	    shell.setText("LogCatSlave");
	    
	    //createMenu(shell);
	    createPanel(shell);
	    
	    shell.pack();
	    //shell.setMaximized(true); //full screen
	    shell.open();

	    while (!shell.isDisposed()) {
	        if (!mDisplay.readAndDispatch())
	            mDisplay.sleep();
	    }
	    mDisplay.dispose();
	}

	private void createMenu(final Shell shell) {
		Menu menuBar = new Menu(shell, SWT.BAR);
		
        MenuItem fileItem = new MenuItem(menuBar, SWT.CASCADE);
        fileItem.setText("&File");
        MenuItem editItem = new MenuItem(menuBar, SWT.CASCADE);
        editItem.setText("&Edit");
        MenuItem aboutItem = new MenuItem(menuBar, SWT.CASCADE);
        aboutItem.setText("&Help");

        Menu fileMenu = new Menu(menuBar);
        fileItem.setMenu(fileMenu);
        Menu editMenu = new Menu(menuBar);
        editItem.setMenu(editMenu);
        Menu aboutMenu = new Menu(menuBar);
        aboutItem.setMenu(aboutMenu);
        
        MenuItem item;
        // -- FILE --
        item = new MenuItem(fileMenu, SWT.NONE);
        item.setText("&Open File");
        item.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String filePath = new FileDialog(shell).open();
                System.out.println(filePath);
            }
        });

        item = new MenuItem(fileMenu, SWT.NONE);
        item.setText("Open &Directory");
        item.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String dirPath = new DirectoryDialog(shell).open();
                System.out.println(dirPath);
            }
        });
        
        new MenuItem(fileMenu, SWT.SEPARATOR);

        item = new MenuItem(fileMenu, SWT.NONE);
        item.setText("E&xit\tCtrl-Q");
        item.setAccelerator('Q' | SWT.MOD1);
        item.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
   
        // -- Edit --
        
        // -- About --
        item = new MenuItem(aboutMenu, SWT.NONE);
        item.setText("&About");
        item.addSelectionListener(new SelectionAdapter(){
//      	@Override
//            public void widgetSelected(SelectionEvent e) {
//        		MessageBox dialog = 
//        				  new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK);
//        				dialog.setText("about");
//        				String msg = "";
//        				msg += "LogCatSlave\n\n";
//        				msg += "V0.01\n";
//        				msg += "KT Chen  2016\n";
//        				dialog.setMessage(msg);
//        				dialog.open();
//            }
        	@Override
            public void widgetSelected(SelectionEvent e) {
        		String msg = 
        				"LogCatSlave V0.01\n"
        			  + "\n"
        			  + "KTChen 2016";
        		MessageDialog.openInformation(shell, "about", msg);
            }
        });
        
        shell.setMenuBar(menuBar);
	}
	
	private void createPanel(final Shell shell) {
		shell.setLayout(new GridLayout(1, false));
        Composite panel = new Composite(shell, SWT.BORDER);
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        panel.setLayout(new GridLayout(2,false));
        
        Composite panel1 = new Composite(panel, SWT.BORDER);
        panel1.setLayoutData(new GridData(GridData.FILL_BOTH));
        panel1.setLayout(new GridLayout());
        Composite panel2 = new Composite(panel, SWT.BORDER);
        panel2.setLayoutData(new GridData(GridData.FILL_BOTH));
        panel2.setLayout(new GridLayout());
        
        mStatusLine = new Label(shell, SWT.NONE);
        mStatusLine.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        mStatusLine.setText("Initializing...");
        
	}
}
