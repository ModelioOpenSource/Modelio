/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.linkeditor.handlers.print;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PrintFigureOperation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.modelio.linkeditor.panel.ILinkEditor;
import org.modelio.linkeditor.plugin.LinkEditor;

@objid ("9cee2abd-a62c-4e70-b579-4e9308645807")
public class PrintPreviewDialog extends Dialog {
    @objid ("9364734b-0139-4e5b-84d3-fe6db4011ccc")
    private int printMode = PrintFigureOperation.FIT_PAGE;

    @objid ("8ea0f4ad-3dd4-4ddd-9d5f-47c3be1e2899")
    private Image image;

    @objid ("5a97cded-2e03-44de-bdd2-2e20e21bfa21")
    private Shell shell;

    @objid ("616eebd0-1fbf-49ec-8109-aeb1427b1983")
    private Canvas canvas;

    @objid ("b119b4a0-facb-4971-bd15-dd6fff188369")
    private Printer printer;

    @objid ("b953bb01-e58c-4271-89a0-b32bf465b816")
    private Label pagesLabel;

    @objid ("4c88c303-a15b-4598-ad82-53213c9260af")
    private PrintPageNavigation pageNavigation;

    @objid ("a3fb9982-7ac3-4b61-8663-70153e98d1fc")
    private Button previousButton;

    @objid ("d7595fe2-32f0-4e20-8724-ece0711c52b1")
    private Button nextButton;

    @objid ("93e3f9b9-c043-489d-a873-481c9d9b5a36")
    private ILinkEditor editor;

    @objid ("41a59eed-9de5-4344-89aa-0c854c1a0a52")
    private PrintMargin margin;

    @objid ("62c49fd0-687c-44cc-9c70-93d613f94ad8")
    public PrintPreviewDialog(Shell parent, ILinkEditor linkEditor) {
        super(parent);
        this.editor = linkEditor;
        this.image = linkEditor.getImage();
        this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
    }

    @objid ("1ff807f9-1a5e-4e9d-8a94-3fd5d83d39c2")
    public void open() {
        this.shell.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Title"));
        this.shell.setLayout(new GridLayout(5, false));
        
        this.canvas = new Canvas(this.shell, SWT.BORDER);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.horizontalSpan = 3;
        gridData.verticalSpan = 2;
        gridData.widthHint = 300;
        this.canvas.setLayoutData(gridData);
        this.canvas.addPaintListener(new PaintListener() {
        
            @Override
            public void paintControl(PaintEvent e) {
                int canvasBorder = 5;
        
                if (PrintPreviewDialog.this.printer == null || PrintPreviewDialog.this.printer.isDisposed()) {
                    return;
                }
        
                Rectangle rectangle = PrintPreviewDialog.this.printer.getBounds();
                Point canvasSize = PrintPreviewDialog.this.canvas.getSize();
        
                double viewScaleFactor = (canvasSize.x - canvasBorder * 2) * 1.0 / rectangle.width;
                viewScaleFactor = Math.min(viewScaleFactor, (canvasSize.y - canvasBorder * 2) * 1.0 / rectangle.height);
                int offsetX = (canvasSize.x - (int) (viewScaleFactor * rectangle.width)) / 2;
                int offsetY = (canvasSize.y - (int) (viewScaleFactor * rectangle.height)) / 2;
        
                e.gc.setBackground(PrintPreviewDialog.this.shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
                e.gc.fillRectangle(offsetX,
                        offsetY,
                        (int) (viewScaleFactor * rectangle.width),
                        (int) (viewScaleFactor * rectangle.height));
                // draws the margin
                e.gc.setLineStyle(SWT.LINE_SOLID);
                e.gc.setForeground(PrintPreviewDialog.this.shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
                e.gc.drawRectangle(offsetX,
                        offsetY,
                        (int) (viewScaleFactor * rectangle.width),
                        (int) (viewScaleFactor * rectangle.height));
        
                if (PrintPreviewDialog.this.image != null) {
                    int imageWidth = PrintPreviewDialog.this.image.getBounds().width;
                    int imageHeight = PrintPreviewDialog.this.image.getBounds().height;
        
                    double dpiScaleFactorX = PrintPreviewDialog.this.printer.getDPI().x * 1.0 / PrintPreviewDialog.this.shell.getDisplay().getDPI().x;
                    double dpiScaleFactorY = PrintPreviewDialog.this.printer.getDPI().y * 1.0 / PrintPreviewDialog.this.shell.getDisplay().getDPI().y;
        
                    double imageSizeFactor = Math.min(rectangle.width * viewScaleFactor / imageWidth,
                            rectangle.height * viewScaleFactor / imageHeight);
                    int destX = (int) (imageSizeFactor * imageWidth) - 1; // Remove 1 in order to offset the surrounding rectangle
                    int destY = (int) (imageSizeFactor * imageHeight) - 1; // Remove 1 in order to offset the surrounding rectangle
        
                    int srcX = 0;
                    int srcY = 0;
        
                    // Printing in real size
                    if (PrintPreviewDialog.this.printMode == PrintFigureOperation.TILE) {
                        destX = (int) (dpiScaleFactorX * imageWidth * viewScaleFactor);
                        destY = (int) (dpiScaleFactorY * imageHeight * viewScaleFactor);
                        int nbPagesX = (int) (destX / (viewScaleFactor * rectangle.width)) + 1;
                        int nbPagesY = (int) (destY / (viewScaleFactor * rectangle.height)) + 1;
                        updateNavigation(nbPagesX, nbPagesY);
        
                        srcX = (int) (rectangle.width / dpiScaleFactorX) * (PrintPreviewDialog.this.pageNavigation.x - 1);
                        srcY = (int) (rectangle.height / dpiScaleFactorY) * (PrintPreviewDialog.this.pageNavigation.y - 1);
        
                        if (PrintPreviewDialog.this.pageNavigation.x * rectangle.width / dpiScaleFactorX > imageWidth) {
                            imageWidth = (int) (imageWidth - ((PrintPreviewDialog.this.pageNavigation.x - 1) * rectangle.width / dpiScaleFactorX));
                        } else {
                            imageWidth = (int) (rectangle.width / dpiScaleFactorX);
                        }
                        if (PrintPreviewDialog.this.pageNavigation.y * rectangle.height / dpiScaleFactorY > imageHeight) {
                            imageHeight = (int) (imageHeight - ((PrintPreviewDialog.this.pageNavigation.y - 1) * rectangle.height / dpiScaleFactorY));
                        } else {
                            imageHeight = (int) (rectangle.height / dpiScaleFactorY);
                        }
        
                        destX = (int) (dpiScaleFactorX * imageWidth * viewScaleFactor) - 1;
                        destY = (int) (dpiScaleFactorY * imageHeight * viewScaleFactor) - 1;
                    }
        
                    e.gc.drawImage(PrintPreviewDialog.this.image,
                            srcX,
                            srcY,
                            imageWidth,
                            imageHeight,
                            offsetX + 1, // On d?cale l'image de 1px pour compenser la pr?sence de la bordure si elle est affich?e
                            offsetY + 1,
                            destX,
                            destY);
        
                }
            }
        
        });
        
        final Group optionsGroup = new Group(this.shell, SWT.NONE);
        optionsGroup.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.PrintOptions"));
        FormLayout optionsLayout = new FormLayout();
        optionsLayout.marginWidth = 5;
        optionsLayout.marginHeight = 5;
        optionsGroup.setLayout(optionsLayout);
        gridData = new GridData(GridData.FILL_HORIZONTAL, SWT.TOP, false, false);
        gridData.verticalSpan = 1;
        gridData.widthHint = 150;
        optionsGroup.setLayoutData(gridData);
        
        Button adjustCheck = new Button(optionsGroup, SWT.CHECK);
        adjustCheck.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.FitToPage"));
        adjustCheck.setSelection(true);
        adjustCheck.addSelectionListener(new SelectionAdapter() {
        
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (((Button) (event.widget)).getSelection() == true) {
                    PrintPreviewDialog.this.printMode = PrintFigureOperation.FIT_PAGE;
                    updateNavigation(1, 1);
                } else {
                    PrintPreviewDialog.this.printMode = PrintFigureOperation.TILE;
                }
                PrintPreviewDialog.this.canvas.redraw();
            }
        });
        
        final Composite cButtons = new Composite(this.shell, SWT.NONE);
        FillLayout orientationLayout = new FillLayout();
        orientationLayout.type = SWT.VERTICAL;
        orientationLayout.marginWidth = 5;
        orientationLayout.marginHeight = 5;
        orientationLayout.spacing = 5;
        cButtons.setLayout(orientationLayout);
        gridData = new GridData(SWT.RIGHT, SWT.TOP, false, false);
        gridData.widthHint = 100;
        gridData.verticalSpan = 3;
        cButtons.setLayoutData(gridData);
        
        Button printButton = new Button(cButtons, SWT.PUSH);
        printButton.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Print"));
        printButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                int style = PrintPreviewDialog.this.shell.getStyle();
                Shell shell_1 = new Shell((style & SWT.MIRRORED) != 0 ? SWT.RIGHT_TO_LEFT : SWT.NONE);
                PrintDialog dialog = new PrintDialog(shell_1, SWT.NULL);
                PrinterData pData = new PrinterData();
                pData.orientation = PrintPreviewDialog.this.printer.getPrinterData().orientation;
                dialog.setPrinterData(pData);
                PrinterData data = dialog.open();
        
                if (data != null) {
                    // PrintPreviewDialog.this.editor.print(data);
                    PrintPreviewDialog.this.printer = new Printer(data);
                    if (PrintPreviewDialog.this.printer.startJob("print Link Editor")) {
                        if (PrintPreviewDialog.this.printer.startPage()) {
                            GC gc = new GC(PrintPreviewDialog.this.printer);
                            ImageData imageData = PrintPreviewDialog.this.image.getImageData();
                            Image printerImage = new Image(PrintPreviewDialog.this.printer, imageData);
                            Point screenDPI = PrintPreviewDialog.this.shell.getDisplay().getDPI();
                            Point printerDPI = PrintPreviewDialog.this.printer.getDPI();
        
                            int srcX = 0;
                            int srcY = 0;
                            int padding = 5;
                            int imageWidth = imageData.width;
                            int imageHeight = imageData.height;
        
                            Rectangle trim = PrintPreviewDialog.this.printer.computeTrim(0, 0, 0, 0);
        
                            int scaleForX = (printerDPI.x - padding * 2) / screenDPI.x;
                            int scaleForY = (printerDPI.y - padding * 2) / screenDPI.y;
                            int scaleFactor = Math.min(scaleForX, scaleForY);
                            int destX = scaleFactor * imageWidth;
                            int destY = scaleFactor * imageHeight;
        
                            if (PrintPreviewDialog.this.printMode == PrintFigureOperation.FIT_PAGE) {
                                scaleForX = (PrintPreviewDialog.this.printer.getBounds().width - padding * 2) / imageWidth;
                                scaleForY = (PrintPreviewDialog.this.printer.getBounds().height - padding * 2) / imageHeight;
                                scaleFactor = Math.min(scaleForX, scaleForY);
                                destX = scaleFactor * imageWidth;
                                destY = scaleFactor * imageHeight;
                            }
                            // Draw the image
                            gc.drawImage(printerImage,
                                    srcX, srcY,
                                    imageWidth,
                                    imageHeight,
                                    -trim.x, -trim.y,
                                    destX,
                                    destY);
        
                            // Clean up
                            printerImage.dispose();
                            gc.dispose();
                            PrintPreviewDialog.this.printer.endPage();
                        }
                    }
                    // End the job and dispose the printer
                    PrintPreviewDialog.this.printer.endJob();
                }
                shell_1.dispose();
            }
        });
        
        final Button closeButton = new Button(cButtons, SWT.PUSH);
        closeButton.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Cancel"));
        closeButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                PrintPreviewDialog.this.shell.dispose();
            }
        });
        
        Group orientationGroup = new Group(this.shell, SWT.NONE);
        orientationGroup.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Orientation"));
        orientationLayout = new FillLayout();
        orientationLayout.type = SWT.VERTICAL;
        orientationLayout.marginWidth = 5;
        orientationLayout.marginHeight = 10;
        orientationGroup.setLayout(orientationLayout);
        gridData = new GridData(GridData.FILL_HORIZONTAL, SWT.TOP, false, false);
        gridData.verticalSpan = 2;
        gridData.widthHint = 150;
        gridData.heightHint = 60;
        orientationGroup.setLayoutData(gridData);
        
        Button[] orientation = new Button[2];
        
        orientation[0] = new Button(orientationGroup, SWT.RADIO);
        orientation[0].setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Portrait"));
        orientation[0].setSelection(true);
        orientation[0].addSelectionListener(new SelectionAdapter() {
        
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (((Button) (event.widget)).getSelection() == true) {
                    PrintPreviewDialog.this.printer.getPrinterData().orientation = PrinterData.PORTRAIT;
                    PrintPreviewDialog.this.printer = new Printer(PrintPreviewDialog.this.printer.getPrinterData());
                    PrintPreviewDialog.this.canvas.redraw();
                }
        
            }
        });
        
        orientation[1] = new Button(orientationGroup, SWT.RADIO);
        orientation[1].setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Landscape"));
        orientation[1].addSelectionListener(new SelectionAdapter() {
        
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (((Button) (event.widget)).getSelection() == true) {
                    PrintPreviewDialog.this.printer.getPrinterData().orientation = PrinterData.LANDSCAPE;
                    PrintPreviewDialog.this.printer = new Printer(PrintPreviewDialog.this.printer.getPrinterData());
                    PrintPreviewDialog.this.canvas.redraw();
                }
        
            }
        });
        
        this.previousButton = new Button(this.shell, SWT.PUSH);
        this.previousButton.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Previous"));
        this.previousButton.setEnabled(false);
        gridData = new GridData(SWT.RIGHT, SWT.TOP, true, false);
        gridData.widthHint = 100;
        this.previousButton.setLayoutData(gridData);
        this.previousButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (PrintPreviewDialog.this.pageNavigation != null && !PrintPreviewDialog.this.pageNavigation.isFirstPage()) {
                    PrintPreviewDialog.this.pageNavigation.goPreviousPage();
                    PrintPreviewDialog.this.canvas.redraw();
                }
                if (PrintPreviewDialog.this.pageNavigation != null) {
                    if (PrintPreviewDialog.this.pageNavigation.isFirstPage()) {
                        PrintPreviewDialog.this.previousButton.setEnabled(false);
                    }
                    if (!PrintPreviewDialog.this.pageNavigation.isLastPage()) {
                        PrintPreviewDialog.this.nextButton.setEnabled(true);
                    }
                }
        
                updatePageCounter();
        
            }
        });
        
        this.nextButton = new Button(this.shell, SWT.PUSH);
        this.nextButton.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Next"));
        this.nextButton.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 100;
        this.nextButton.setLayoutData(gridData);
        this.nextButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (PrintPreviewDialog.this.pageNavigation != null && !PrintPreviewDialog.this.pageNavigation.isLastPage()) {
                    PrintPreviewDialog.this.pageNavigation.goNextPage();
                    PrintPreviewDialog.this.canvas.redraw();
                }
                if (PrintPreviewDialog.this.pageNavigation != null) {
                    if (PrintPreviewDialog.this.pageNavigation.isLastPage()) {
                        PrintPreviewDialog.this.nextButton.setEnabled(false);
                    }
                    if (!PrintPreviewDialog.this.pageNavigation.isFirstPage()) {
                        PrintPreviewDialog.this.previousButton.setEnabled(true);
                    }
                }
        
                updatePageCounter();
        
            }
        });
        
        this.pagesLabel = new Label(this.shell, SWT.NONE);
        this.pagesLabel.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Page") + " 1/1");
        gridData = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
        gridData.widthHint = 100;
        this.pagesLabel.setLayoutData(gridData);
        
        this.shell.setSize(600, 500);
        this.shell.setMinimumSize(600, 500);
        this.shell.open();
        setPrinter(null, 1.0);
        
        // Set up the event loop.
        while (!this.shell.isDisposed()) {
            if (!this.shell.getDisplay().readAndDispatch()) {
                // If no more entries in event queue
                this.shell.getDisplay().sleep();
            }
        }
        
        if (this.image != null) {
            this.image.dispose();
        }
    }

    @objid ("b2e96d60-4a0c-482e-a8fb-d4ee92de7f5b")
    private void setPrinter(Printer p_printer, double marginSize) {
        if (p_printer == null) {
            PrinterData printerData = Printer.getDefaultPrinterData();
            if (printerData == null) {
                // Linux may have one or more printers without a default printer
                PrinterData[] list = Printer.getPrinterList();
                if (list.length > 0) {
                    printerData = list[0];
                }
            }
            this.printer = new Printer(printerData);
        } else {
            this.printer = p_printer;
        }
        this.margin = PrintMargin.getPrintMargin(this.printer, marginSize);
        this.canvas.redraw();
    }

    @objid ("a9955a88-828c-4003-8c0f-c4d4ec73517b")
    private void updatePageCounter() {
        this.pagesLabel.setText(LinkEditor.I18N.getString("Gui.PrintPreviewDialog.Page") + " " + this.pageNavigation.getCurrentPageNumber() + "/" + this.pageNavigation.getTotalPages());
    }

    @objid ("6422279e-9d4b-44fa-aee2-4290ea2542b0")
    private void updateNavigation(int nbPagesX, int nbPagesY) {
        if (this.pageNavigation == null) {
            this.pageNavigation = new PrintPageNavigation(nbPagesX, nbPagesY);
            if (nbPagesX > 1 || nbPagesY > 1) {
                this.nextButton.setEnabled(true);
            }
        } else {
            if (nbPagesX > 1 || nbPagesY > 1) {
                if (!(this.pageNavigation.getNbPagesX() == nbPagesX &&
                        this.pageNavigation.getNbPagesY() == nbPagesY)) {
                    this.pageNavigation.setNbPagesX(nbPagesX);
                    this.pageNavigation.setNbPagesY(nbPagesY);
                    this.pageNavigation.x = 1;
                    this.pageNavigation.y = 1;
                }
                if (this.pageNavigation.isFirstPage()) {
                    this.previousButton.setEnabled(false);
                } else {
                    this.previousButton.setEnabled(true);
                }
                if (this.pageNavigation.isLastPage()) {
                    this.nextButton.setEnabled(false);
                } else {
                    this.nextButton.setEnabled(true);
                }
            } else {
                this.pageNavigation.x = 1;
                this.pageNavigation.y = 1;
                this.pageNavigation.setNbPagesX(1);
                this.pageNavigation.setNbPagesY(1);
                this.previousButton.setEnabled(false);
                this.nextButton.setEnabled(false);
            }
        }
        
        updatePageCounter();
    }

}
