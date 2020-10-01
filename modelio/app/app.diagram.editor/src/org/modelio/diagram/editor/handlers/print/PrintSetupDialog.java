/* 
 * Copyright 2013-2020 Modeliosoft
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

package org.modelio.diagram.editor.handlers.print;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.PrintFigureOperation;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.print.PrintGraphicalViewerOperation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.ImageBuilder;

/**
 * @author apedro Dialog box for print setup with preview image
 */
@objid ("65b93f47-33f7-11e2-95fe-001ec947c8cc")
public class PrintSetupDialog extends Dialog {
    @objid ("65b93f4e-33f7-11e2-95fe-001ec947c8cc")
     int printMode = PrintFigureOperation.FIT_PAGE;

    @objid ("91387739-edb1-427d-b56a-e370fd142df9")
    public RootEditPart rootEditPart;

    @objid ("65b93f4c-33f7-11e2-95fe-001ec947c8cc")
     PrintMargin margin;

    @objid ("65bba186-33f7-11e2-95fe-001ec947c8cc")
     PrintPageNavigation pageNavigation;

    @objid ("98d94416-b55a-421c-af72-9d41c6e83f5e")
    public Shell shell;

    @objid ("77d8f0e7-abc8-4e9d-ac2b-fd94597b29aa")
    public Canvas canvas;

    @objid ("addb50a6-0202-436e-a73c-0ff1448dbf64")
    public Printer printer;

    @objid ("2ffee4b8-aa83-4fb4-a59b-5ee3bab18443")
    public Image image;

    @objid ("19c689a3-ee4c-48e6-9731-d4ac6213a654")
    public Button previousButton;

    @objid ("6f5ce91d-683c-4394-9ad2-4e047ebcae0f")
    public Button nextButton;

    @objid ("1f3d1472-52a1-482d-b0c0-8cd5c3b500dd")
    public Label pagesLabel;

    @objid ("65bba188-33f7-11e2-95fe-001ec947c8cc")
    public PrintSetupDialog(RootEditPart rootEditPart) {
        super(Display.getCurrent().getActiveShell());
        this.rootEditPart = rootEditPart;
        ImageBuilder imageBuilder = new ImageBuilder();
        this.image = imageBuilder.makeImage(rootEditPart);
    }

    @objid ("65bba18b-33f7-11e2-95fe-001ec947c8cc")
    public void open() {
        this.shell = new Shell(Display.getCurrent().getActiveShell(),
                SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
        this.shell.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Title"));
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
        
                if (PrintSetupDialog.this.printer == null || PrintSetupDialog.this.printer.isDisposed()) {
                    return;
                }
                Rectangle rectangle = PrintSetupDialog.this.printer.getBounds();
                Point canvasSize = PrintSetupDialog.this.canvas.getSize();
        
                double viewScaleFactor = (canvasSize.x - canvasBorder * 2) * 1.0 / rectangle.width;
                viewScaleFactor = Math.min(viewScaleFactor,
                        (canvasSize.y - canvasBorder * 2) * 1.0 / rectangle.height);
        
                int offsetX = (canvasSize.x - (int) (viewScaleFactor * rectangle.width)) / 2;
                int offsetY = (canvasSize.y - (int) (viewScaleFactor * rectangle.height)) / 2;
        
                e.gc.setBackground(PrintSetupDialog.this.shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
                // draws the page layout
                e.gc.fillRectangle(offsetX,
                        offsetY,
                        (int) (viewScaleFactor * rectangle.width),
                        (int) (viewScaleFactor * rectangle.height));
        
                // draws the margin
                e.gc.setLineStyle(SWT.LINE_SOLID);
                e.gc.setForeground(PrintSetupDialog.this.shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
        
                e.gc.drawRectangle(offsetX,
                        offsetY,
                        (int) (viewScaleFactor * rectangle.width),
                        (int) (viewScaleFactor * rectangle.height));
        
                if (PrintSetupDialog.this.image != null) {
                    int imageWidth = PrintSetupDialog.this.image.getBounds().width;
                    int imageHeight = PrintSetupDialog.this.image.getBounds().height;
        
                    double dpiScaleFactorX = PrintSetupDialog.this.printer.getDPI().x * 1.0 / PrintSetupDialog.this.shell.getDisplay().getDPI().x;
                    double dpiScaleFactorY = PrintSetupDialog.this.printer.getDPI().y * 1.0 / PrintSetupDialog.this.shell.getDisplay().getDPI().y;
        
                    double imageSizeFactor = Math.min(rectangle.width * viewScaleFactor / imageWidth,
                            rectangle.height * viewScaleFactor / imageHeight);
                    int destX = (int) (imageSizeFactor * imageWidth) - 1; // Remove 1 in order to offset the surrounding rectangle
                    int destY = (int) (imageSizeFactor * imageHeight) - 1; // Remove 1 in order to offset the surrounding rectangle
        
                    int srcX = 0;
                    int srcY = 0;
                    // Printing in real size
                    if (PrintSetupDialog.this.printMode == PrintFigureOperation.TILE) {
                        destX = (int) (dpiScaleFactorX * imageWidth * viewScaleFactor);
                        destY = (int) (dpiScaleFactorY * imageHeight * viewScaleFactor);
                        int nbPagesX = (int) (destX / (viewScaleFactor * rectangle.width)) + 1;
                        int nbPagesY = (int) (destY / (viewScaleFactor * rectangle.height)) + 1;
                        updateNavigation(nbPagesX, nbPagesY);
        
                        srcX = (int) (rectangle.width / dpiScaleFactorX) * (PrintSetupDialog.this.pageNavigation.x - 1);
                        srcY = (int) (rectangle.height / dpiScaleFactorY) * (PrintSetupDialog.this.pageNavigation.y - 1);
        
                        if (PrintSetupDialog.this.pageNavigation.x * rectangle.width / dpiScaleFactorX > imageWidth) {
                            imageWidth = (int) (imageWidth - ((PrintSetupDialog.this.pageNavigation.x - 1) * rectangle.width / dpiScaleFactorX));
                        } else {
                            imageWidth = (int) (rectangle.width / dpiScaleFactorX);
                        }
                        if (PrintSetupDialog.this.pageNavigation.y * rectangle.height / dpiScaleFactorY > imageHeight) {
                            imageHeight = (int) (imageHeight - ((PrintSetupDialog.this.pageNavigation.y - 1) * rectangle.height / dpiScaleFactorY));
                        } else {
                            imageHeight = (int) (rectangle.height / dpiScaleFactorY);
                        }
        
                        destX = (int) (dpiScaleFactorX * imageWidth * viewScaleFactor) - 1;
                        destY = (int) (dpiScaleFactorY * imageHeight * viewScaleFactor) - 1;
                    }
        
                    e.gc.drawImage(PrintSetupDialog.this.image,
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
        optionsGroup.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.PrintOptions"));
        FormLayout optionsLayout = new FormLayout();
        optionsLayout.marginWidth = 5;
        optionsLayout.marginHeight = 5;
        optionsGroup.setLayout(optionsLayout);
        gridData = new GridData(GridData.FILL_HORIZONTAL, SWT.TOP, false, false);
        gridData.verticalSpan = 1;
        gridData.widthHint = 150;
        optionsGroup.setLayoutData(gridData);
        
        Button adjustCheck = new Button(optionsGroup, SWT.CHECK);
        adjustCheck.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.FitToPage"));
        adjustCheck.setSelection(true);
        adjustCheck.addSelectionListener(new SelectionAdapter() {
        
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (((Button) (event.widget)).getSelection() == true) {
                    PrintSetupDialog.this.printMode = PrintFigureOperation.FIT_PAGE;
                    updateNavigation(1, 1);
                } else {
                    PrintSetupDialog.this.printMode = PrintFigureOperation.TILE;
                }
                PrintSetupDialog.this.canvas.redraw();
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
        printButton.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Print"));
        printButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                int style = PrintSetupDialog.this.shell.getStyle();
                Shell shell1 = new Shell((style & SWT.MIRRORED) != 0 ? SWT.RIGHT_TO_LEFT : SWT.NONE);
                PrintDialog dialog = new PrintDialog(shell1, SWT.NULL);
                PrinterData pData = new PrinterData();
                pData.orientation = PrintSetupDialog.this.printer.getPrinterData().orientation;
                dialog.setPrinterData(pData);
                PrinterData data = dialog.open();
        
                if (data != null) {
                    LayerManager lm = (LayerManager) PrintSetupDialog.this.rootEditPart;
        
                    // Temporarily add the background layer to the "printable layers" set so that it is present in the saved image
                    IFigure backgroundLayer = lm.getLayer("BACKGROUND_LAYER");
                    Layer printableLayers = (Layer) lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
                    printableLayers.add(backgroundLayer, "BACKGROUND_LAYER", 0);
        
                    PrintGraphicalViewerOperation operation = new PrintGraphicalViewerOperation(new Printer(data),
                            (GraphicalViewer) PrintSetupDialog.this.rootEditPart.getViewer());
        
                    // here you can set the Print Mode
                    // operation.setPrintMode(PrintFigureOperation.TILE);
                    operation.setPrintMode(PrintSetupDialog.this.printMode);
        
                    operation.run("Printing diagram");
        
                    // Restore the background layer to its initial placement
                    printableLayers.remove(backgroundLayer);
                    Layer scalableLayers = (Layer) lm.getLayer(LayerConstants.SCALABLE_LAYERS);
                    scalableLayers.add(backgroundLayer, "BACKGROUND_LAYER", 0);
                }
                shell1.dispose();
            }
        });
        
        final Button closeButton = new Button(cButtons, SWT.PUSH);
        closeButton.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Cancel"));
        closeButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                PrintSetupDialog.this.shell.dispose();
            }
        });
        
        Group orientationGroup = new Group(this.shell, SWT.NONE);
        orientationGroup.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Orientation"));
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
        
        Button portraitButton = new Button(orientationGroup, SWT.RADIO);
        portraitButton.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Portrait"));
        portraitButton.setSelection(true);
        portraitButton.addSelectionListener(new SelectionAdapter() {
        
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (((Button) (event.widget)).getSelection() == true) {
                    PrintSetupDialog.this.printer.getPrinterData().orientation = PrinterData.PORTRAIT;
                    PrintSetupDialog.this.printer = new Printer(PrintSetupDialog.this.printer.getPrinterData());
                    PrintSetupDialog.this.canvas.redraw();
                }
        
            }
        });
        
        Button landscapeButton = new Button(orientationGroup, SWT.RADIO);
        landscapeButton.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Landscape"));
        landscapeButton.addSelectionListener(new SelectionAdapter() {
        
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (((Button) (event.widget)).getSelection() == true) {
                    PrintSetupDialog.this.printer.getPrinterData().orientation = PrinterData.LANDSCAPE;
                    PrintSetupDialog.this.printer = new Printer(PrintSetupDialog.this.printer.getPrinterData());
                    PrintSetupDialog.this.canvas.redraw();
                }
        
            }
        });
        
        this.previousButton = new Button(this.shell, SWT.PUSH);
        this.previousButton.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Previous"));
        this.previousButton.setEnabled(false);
        gridData = new GridData(SWT.RIGHT, SWT.TOP, true, false);
        gridData.widthHint = 100;
        this.previousButton.setLayoutData(gridData);
        this.previousButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (PrintSetupDialog.this.pageNavigation != null && !PrintSetupDialog.this.pageNavigation.isFirstPage()) {
                    PrintSetupDialog.this.pageNavigation.goPreviousPage();
                    PrintSetupDialog.this.canvas.redraw();
                }
                if (PrintSetupDialog.this.pageNavigation != null) {
                    if (PrintSetupDialog.this.pageNavigation.isFirstPage()) {
                        PrintSetupDialog.this.previousButton.setEnabled(false);
                    }
                    if (!PrintSetupDialog.this.pageNavigation.isLastPage()) {
                        PrintSetupDialog.this.nextButton.setEnabled(true);
                    }
                }
        
                updatePageCounter();
        
            }
        });
        
        this.nextButton = new Button(this.shell, SWT.PUSH);
        this.nextButton.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Next"));
        this.nextButton.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        gridData.widthHint = 100;
        this.nextButton.setLayoutData(gridData);
        this.nextButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (PrintSetupDialog.this.pageNavigation != null && !PrintSetupDialog.this.pageNavigation.isLastPage()) {
                    PrintSetupDialog.this.pageNavigation.goNextPage();
                    PrintSetupDialog.this.canvas.redraw();
                }
                if (PrintSetupDialog.this.pageNavigation != null) {
                    if (PrintSetupDialog.this.pageNavigation.isLastPage()) {
                        PrintSetupDialog.this.nextButton.setEnabled(false);
                    }
                    if (!PrintSetupDialog.this.pageNavigation.isFirstPage()) {
                        PrintSetupDialog.this.previousButton.setEnabled(true);
                    }
                }
        
                updatePageCounter();
        
            }
        });
        
        this.pagesLabel = new Label(this.shell, SWT.NONE);
        this.pagesLabel.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Page") + " 1/1");
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

    /**
     * Sets target printer.
     * @param printer
     */
    @objid ("65bba18e-33f7-11e2-95fe-001ec947c8cc")
    void setPrinter(Printer p_printer, double marginSize) {
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

    @objid ("65bba193-33f7-11e2-95fe-001ec947c8cc")
    void updateNavigation(int nbPagesX, int nbPagesY) {
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

    @objid ("65bba197-33f7-11e2-95fe-001ec947c8cc")
    void updatePageCounter() {
        this.pagesLabel.setText(DiagramEditor.I18N.getString("Gui.PrintSetupDialog.Page") + " " + this.pageNavigation.getCurrentPageNumber() + "/" + this.pageNavigation.getTotalPages());
    }

}
