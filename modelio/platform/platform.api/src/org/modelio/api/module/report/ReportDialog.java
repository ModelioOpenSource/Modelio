/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.module.report;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.navigation.INavigationService;
import org.modelio.api.plugin.Api;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.platform.rcp.system.ModelioHelpSystem;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIImages;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * The report dialog is used by modules willing to display the contents of a {@link Report} which they have built during a processing operation by adding {@link ReportEntry} records to it.
 * 
 * A Report is a sequence of ReportEntry. A ReportEntry can be of several kind (see @link {@link EntryKind}. The contents of the report are displayed in HTML rendered text in a browser.
 * 
 * Two kind of links are exclusively supported:
 * <ul>
 * <li>modelio model element links that allow to navigate to a given element listed in the report</li>
 * <li>modelio help topic links that pops the Modelio help for a given topic.</li>
 * </ul>
 * The ReportDialog browser is configured to ignore any other type of link.
 * 
 * The @link {@link HTMLReport} private class is in charge to lay out the reported message in HTML text, controlled by its internal CSS stylesheet {@link HTMLReport#CSS}.
 * 
 * <p>
 * Typical use:
 * 
 * <pre>
 * Report myReport = new Report();
 * myReport.addError(...);
 * myReport.addWarning(...);
 * myReport.addTip(...);
 * myReport.addInfo(...);
 * 
 * ReportDialog dlg = new ReportDialog(parent, navigationService, modelioHelpService, myReport);
 * dlg.open();
 * </pre>
 * </p>
 * 
 * @since 4.1
 */
@objid ("a517d575-5af7-431c-8070-ebb7e9617a06")
public class ReportDialog extends ModelioDialog {
    @objid ("c013cc68-1401-4dac-ae4a-9b6cb6910388")
    private static boolean showInfos = false;

    @objid ("5f54f3a9-07aa-4d02-8c97-ed7b52ee2c34")
    private static boolean showTips = false;

    @objid ("a571c061-e442-4988-a2b3-2d2ac9dfe33e")
    private static boolean showWarnings = true;

    @objid ("69608667-245e-4819-b2e5-4363bae10310")
    private static boolean showErrors = true;

    @objid ("a527ecdb-ac6c-4a5c-a9de-4c6939b394f3")
    private static int sortMode;

    /**
     * The Modelio help service used to displays help topics from Modelio documentation.
     */
    @objid ("a8dbbac1-5eb2-46c7-a0ac-ee23c2e6b8d4")
    IWorkbenchHelpSystem helpService;

    @objid ("7c3eb779-0344-406e-9fa1-8622b74e419f")
    private Browser browser;

    @objid ("e9c51ee9-ff0b-49e5-8035-3db176ecebb7")
    private Image sortSequence;

    @objid ("de1bbbc3-1718-475c-9d70-51da1f33b4d7")
    private Image sortCode;

    @objid ("b5b052b3-031b-4c18-8f33-e2bd0c98cea7")
    private Image sortSeverity;

    @objid ("452b28fd-2288-44dc-a36a-e2a2791b142c")
    private Image sortElement;

    /**
     * The Report being displayed
     */
    @objid ("ff4e6d96-3378-449e-b9c9-8d87d830489e")
    private final Report report;

    /**
     * The Modelio navigation service used select elements in Modelio.
     */
    @objid ("1a470a1d-a387-4761-a933-1b5641004179")
    private INavigationService navigationService;

    @objid ("10951397-e353-4e5c-a2ca-9da9a9839d53")
    private IModelingSession session;

    /**
     * C'tor
     * @param parentShell the parent shell. Can be <code>null</code>.
     * @param session access to the project's model. Can be <code>null</code> in which case element links are not displayed and no navigation is possible..
     * @param navigationService the Modelio navigation service. Can be <code>null</code> in which case element links are not displayed and no navigation is possible.
     * @param report the report to display. Cannot be <code>null</code>.
     */
    @objid ("e541a99c-7ee0-45d1-abff-80fda5cf5442")
    public  ReportDialog(Shell parentShell, final IModelingSession session, final INavigationService navigationService, Report report) {
        super(parentShell);
        setShellStyle(SWT.SHELL_TRIM | getDefaultOrientation());
        
        this.session = session;
        this.navigationService = navigationService;
        this.report = report;
        this.helpService = ModelioHelpSystem.getInstance();
        
    }

    @objid ("74e69d11-4e05-4b9a-8f8e-77c63dcf688e")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
    }

    @objid ("01da107f-9dbd-4826-b334-32ffd6703680")
    @Override
    protected void init() {
        // Nothing to do
    }

    @objid ("66587b66-f364-4817-bb64-7834dd04a5ac")
    @SuppressWarnings ("unused")
    @Override
    protected Control createContentArea(Composite parent) {
        this.sortSequence = AbstractUIPlugin.imageDescriptorFromPlugin(Api.PLUGIN_ID, "images/sortsequence.png").createImage();
        this.sortCode = AbstractUIPlugin.imageDescriptorFromPlugin(Api.PLUGIN_ID, "images/sortcode.png").createImage();
        this.sortSeverity = AbstractUIPlugin.imageDescriptorFromPlugin(Api.PLUGIN_ID, "images/sortseverity.png").createImage();
        this.sortElement = AbstractUIPlugin.imageDescriptorFromPlugin(Api.PLUGIN_ID, "images/sortelement.png").createImage();
        
        ToolBar tb = new ToolBar(parent, SWT.NONE);
        tb.setBackground(UIColor.WHITE);
        tb.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
        
        final ToolItem errorsCheckButton = new ToolItem(tb, SWT.BORDER | SWT.CHECK);
        errorsCheckButton.setImage(UIImages.ERROR);
        errorsCheckButton.setToolTipText(Api.I18N.getString("ReportDialog.ShowErrors.tooltip"));
        errorsCheckButton.setSelection(ReportDialog.showErrors);
        errorsCheckButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter((e) -> onShowErrors(((ToolItem) e.widget).getSelection())));
        
        final ToolItem warningsCheckButton = new ToolItem(tb, SWT.BORDER | SWT.CHECK);
        warningsCheckButton.setImage(UIImages.WARNING);
        warningsCheckButton.setToolTipText(Api.I18N.getString("ReportDialog.ShowWarnings.tooltip"));
        warningsCheckButton.setSelection(ReportDialog.showWarnings);
        warningsCheckButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter((e) -> onShowWarnings(((ToolItem) e.widget).getSelection())));
        
        final ToolItem tipsCheckButton = new ToolItem(tb, SWT.CHECK);
        tipsCheckButton.setImage(UIImages.INFO);
        tipsCheckButton.setToolTipText(Api.I18N.getString("ReportDialog.ShowTips.tooltip"));
        tipsCheckButton.setSelection(ReportDialog.showTips);
        tipsCheckButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter((e) -> onShowTips(((ToolItem) e.widget).getSelection())));
        
        final ToolItem infosCheckButton = new ToolItem(tb, SWT.CHECK);
        infosCheckButton.setImage(UIImages.TIP);
        infosCheckButton.setToolTipText(Api.I18N.getString("ReportDialog.ShowInfos.tooltip"));
        infosCheckButton.setSelection(ReportDialog.showInfos);
        infosCheckButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter((e) -> onShowInfos(((ToolItem) e.widget).getSelection())));
        
        new ToolItem(tb, SWT.SEPARATOR);
        
        final ToolItem bySequenceCheckButton = new ToolItem(tb, SWT.RADIO);
        bySequenceCheckButton.setImage(this.sortSequence);
        bySequenceCheckButton.setToolTipText(Api.I18N.getString("ReportDialog.SortBySequence.tooltip"));
        bySequenceCheckButton.setSelection(ReportDialog.sortMode == 0);
        bySequenceCheckButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter((e) -> onSortChange(0, ((ToolItem) e.widget).getSelection())));
        
        final ToolItem bySeverityCheckButton = new ToolItem(tb, SWT.RADIO);
        bySeverityCheckButton.setImage(this.sortSeverity);
        bySeverityCheckButton.setToolTipText(Api.I18N.getString("ReportDialog.SortBySeverity.tooltip"));
        bySeverityCheckButton.setSelection(ReportDialog.sortMode == 1);
        bySeverityCheckButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter((e) -> onSortChange(1, ((ToolItem) e.widget).getSelection())));
        
        final ToolItem byCodeCheckButton = new ToolItem(tb, SWT.RADIO);
        byCodeCheckButton.setImage(this.sortCode);
        byCodeCheckButton.setToolTipText(Api.I18N.getString("ReportDialog.SortByCode.tooltip"));
        byCodeCheckButton.setSelection(ReportDialog.sortMode == 2);
        byCodeCheckButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter((e) -> onSortChange(2, ((ToolItem) e.widget).getSelection())));
        
        final ToolItem byElementCheckButton = new ToolItem(tb, SWT.RADIO);
        byElementCheckButton.setImage(this.sortElement);
        byElementCheckButton.setToolTipText(Api.I18N.getString("ReportDialog.SortByElement.tooltip"));
        byElementCheckButton.setSelection(ReportDialog.sortMode == 3);
        byElementCheckButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter((e) -> onSortChange(3, ((ToolItem) e.widget).getSelection())));
        
        this.browser = new Browser(parent, SWT.NONE);
        this.browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.browser.addLocationListener(new LocationAdapter() {
            @Override
            public void changing(LocationEvent event) {
                if (Objects.equals(event.location, "about:blank")) {
                    return;
                }
                try {
                    URI uri = new URI(event.location);
                    if (Objects.equals(uri.getScheme(), "modelio")) {
                        if (Objects.equals(uri.getPath(), "/element")) {
                            String[] frags = uri.getFragment().split(":");
                            Decoder urlDecoder = Base64.getUrlDecoder();
                            MRef mRef = new MRef(
                                    new String(urlDecoder.decode(frags[0])),
                                    new String(urlDecoder.decode(frags[1])),
                                    frags.length > 2 ? new String(urlDecoder.decode(frags[2])) : "");
                            MObject target = ReportDialog.this.session != null ? ReportDialog.this.session.findByRef(mRef) : null;
                            ReportDialog.this.navigationService.fireNavigate(target);
                        } else if (Objects.equals(uri.getPath(), "/help")) {
                            if (ReportDialog.this.helpService != null) {
                                ReportDialog.this.helpService.displayHelpResource(uri.getFragment());
                            }
                        }
                    }
        
                } catch (URISyntaxException e) {
                    Api.LOG.error("Invalid URI " + event.location);
                    Api.LOG.debug(e);
                }
                event.doit = false;
            }
        });
        refreshContents();
        getShell().setText(this.report.getTitle());
        return this.browser;
    }

    @objid ("8b399229-f4e0-41b7-8e98-1b4e9d4b13ef")
    @Override
    protected Point getInitialSize() {
        return new Point(800, 800);
    }

    @objid ("5d948804-4edc-4613-acdd-24fd7b5abb5f")
    private void onShowInfos(boolean selection) {
        if (selection != ReportDialog.showInfos) {
            ReportDialog.showInfos = selection;
            refreshContents();
        }
        
    }

    @objid ("9f0fb41e-c123-4d79-bf31-714e61f51282")
    private void refreshContents() {
        boolean withNavigation = this.navigationService != null && this.session != null;
        boolean withHelp = this.helpService != null;
        
        HTMLReport htmlreport = new HTMLReport(this.report, withNavigation, withHelp)
                .withContents(ReportDialog.showErrors, ReportDialog.showWarnings, ReportDialog.showTips, ReportDialog.showInfos)
                .withSortMode(ReportDialog.sortMode);
        
        this.browser.setText(htmlreport.getText());
        
    }

    @objid ("fe9d7faa-9b93-4b04-99b3-7475f54f9bba")
    private void onShowTips(boolean selection) {
        if (selection != ReportDialog.showTips) {
            ReportDialog.showTips = selection;
            refreshContents();
        }
        
    }

    @objid ("e302f676-6342-462d-8273-aaf6c7572f33")
    private void onShowWarnings(boolean selection) {
        if (selection != ReportDialog.showWarnings) {
            ReportDialog.showWarnings = selection;
            refreshContents();
        }
        
    }

    @objid ("a1a038a6-9625-4dcb-a36b-0df14acd5d1a")
    private void onShowErrors(boolean selection) {
        if (selection != ReportDialog.showErrors) {
            ReportDialog.showErrors = selection;
            refreshContents();
        }
        
    }

    @objid ("61f0a515-c230-4b89-9f2c-a0ca2003c8e8")
    private void onSortChange(int sortMode, boolean selection) {
        if (ReportDialog.sortMode != sortMode && selection) {
            ReportDialog.sortMode = sortMode;
            refreshContents();
        }
        
    }

    /**
     * This class renders the report s a HTML document string.
     */
    @objid ("de918d8a-0fc9-4004-bc2f-3693005c3073")
    private static class HTMLReport {
        @objid ("23b00d41-7428-4b0d-9a19-4541ee7b1e2a")
        private final String CSS = "<style>\n"
                        + "body          {background-color: #F8F8FB; /*Docaposte Light Grey */ color: #666D92; /* Docaposte Lynch */; font-family: \"Barlow\", \"Barlow-Regular\", -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, Oxygen-Sans, Ubuntu, Cantarell, \"Helvetica Neue\", sans-serif;}\n"
                        + ".title        {font-size:1.5em;font-weight:bold;color:#00008C; /* ultramarine */}\n"
                        + ".summary      {font-size:0.8em;margin-bottom:12px;color:#00008C; /* ultramarine */}\n"
                        + "#diagnostics  {width:100%;font-size:0.90em;overflow-x:auto;border-collapse:collapse;}\n"
                        + ".ERROR        {color:red;}\n"
                        + ".WARNING      {color:darkorange;}\n"
                        + ".TIP          {color:green;}\n"
                        + ".INFO         {font-weight:bold;color:#00008C; /* ultramarine */}\n"
                        + "#code         {width:4em;text-align:left;}\n"
                        + "#message      {word-break:white-space:-o-pre-wrap;word-wrap:break-word;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;}\n"
                        + "#category     {width:5%;}\n"
                        + "#links        {width:20%;word-break:white-space:-o-pre-wrap;word-wrap:break-word;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;}\n"
                        + "#help         {width:1em;text-align:right;}\n"
                        + "#info         {padding-top:12px; padding-bottom:2px;border-bottom: 1px solid #00008C; /* ultramarine */}\n"
                        + "td            {vertical-align: top; border-bottom: 1px dotted #666D92; /* Docaposte Lynch */}\n"
                        + "</style>\n";

        @objid ("eae94003-761b-4fe4-b4dd-f8ef1235d0a4")
        private String text;

        @objid ("5a539b51-1966-4a74-a610-dd81da3d88c3")
        private boolean withNavigation;

        @objid ("30bbcc64-4d74-4f03-b849-134b97871dc1")
        private boolean withHelp;

        @objid ("36237dd5-4ca8-4d8c-b43c-a625d96205fd")
        private boolean withErrors;

        @objid ("b8ae436a-edc2-44b4-966a-4a5fc80395ae")
        private boolean withWarnings;

        @objid ("cc39066f-95fa-4d66-86d1-304ebaa2faf5")
        private boolean withTips;

        @objid ("0c15b213-3fa9-4160-bfa9-33642e991e6a")
        private boolean withInfos;

        @objid ("99abf0ee-20bd-4914-a167-46f8b969447b")
        private int sortMode;

        @objid ("d4a7ac5f-5fe4-4fa1-a68b-cfacf2070cd5")
        private Report report;

        @objid ("7451f33b-52e9-46b6-813a-34b08fb05493")
        public  HTMLReport(Report report, boolean withNavigation, boolean withHelp) {
            this.withNavigation = withNavigation;
            this.withHelp = withHelp;
            this.report = report;
            
        }

        @objid ("ae9b19e2-2ed1-4343-a495-79b2829fd235")
        public String getText() {
            if (this.text == null) {
                this.text = produce();
            }
            return this.text;
        }

        @objid ("157abc77-d7c9-4345-bb49-0ae56aec5e88")
        private String produce() {
            StringBuilder html = new StringBuilder();
            String title = this.report.getTitle() != null ? this.report.getTitle() : "";
            
            // Produce the html document header
            html.append(String.format("<!DOCTYPE html>\n <html><head>%s<title>%s</title></head><body>\n", this.CSS, title));
            
            // Produce the report title and summary
            produceTitle(html);
            
            // Produce report entries
            List<ReportEntry> entries = this.report.getEntries();
            List<ReportEntry> sortedEntries = null;
            
            switch (this.sortMode) {
            case 3: // by element
                sortedEntries = sortByElement(entries);
                break;
            case 2: // by code
                sortedEntries = sortByCode(entries);
                break;
            case 1: // by severity
                sortedEntries = sortBySeverity(entries);
                break;
            case 0: // by occuring sequence
            default:
                sortedEntries = entries;
            }
            
            produceTable(html, sortedEntries);
            
            html.append(String.format("</body></html>\n"));
            return html.toString();
        }

        @objid ("77010baf-a9d6-411f-8b47-6e0c9fbdd577")
        private void produceTable(StringBuilder html, List<ReportEntry> entries) {
            html.append("<table id='diagnostics'>\n");
            
            for (ReportEntry e : entries) {
                switch (e.getKind()) {
                case ERROR:
                    produceErrorRow(html, e);
                    break;
                case TIP:
                    produceTipRow(html, e);
                    break;
                case WARNING:
                    produceWarningRow(html, e);
                    break;
                case INFO:
                default:
                    produceInfoRow(html, e);
                    break;
                }
            }
            html.append(String.format("</table>\n"));
            
        }

        @objid ("0a592760-4e18-459c-a99e-89fe86dd1d70")
        private void produceTitle(StringBuilder html) {
            if (!this.report.getTitle().isEmpty()) {
                html.append(String.format("<div class='title'>%s</div>\n", this.report.getTitle()));
            }
            long nErrors = this.report.getEntries().stream().filter(ReportEntry::isError).count();
            long nWarnings = this.report.getEntries().stream().filter(ReportEntry::isWarning).count();
            html.append(String.format("<div class='summary'>%s, %d Error(s), %d Warning(s)</div>\n", new Date().toString(), nErrors, nWarnings));
            
        }

        @objid ("ff1846fb-0ef4-4df2-8a33-598eacb5bcd9")
        private void produceErrorRow(StringBuilder html, ReportEntry e) {
            if (this.withErrors) {
                produceDiagnosticRow(html, e);
            }
            
        }

        @objid ("5259dadf-d6fa-4da6-ac93-34a817926fb3")
        private void produceWarningRow(StringBuilder html, ReportEntry e) {
            if (this.withWarnings) {
                produceDiagnosticRow(html, e);
            }
            
        }

        @objid ("927e7a71-21d6-4e0d-9568-3d9a4a94ce57")
        private void produceTipRow(StringBuilder html, ReportEntry e) {
            if (this.withTips) {
                produceDiagnosticRow(html, e);
            }
            
        }

        /**
         * Produce HTML links for the linked elements.<br>
         * The href syntax is <code>"modelio:/element#'ArchiMate'%20%7Bb18a2429-49a6-499e-9cfe-1222471f3635%7D%20Archimate.ArchimateProject"</code> which is later recognized by the browser location listener and intercepted to select the corresponding
         * element in Modelio.
         */
        @objid ("8379d5d3-8347-498b-831f-00aaf2f6ef70")
        private void produceLinksCell(StringBuilder html, ReportEntry e) {
            html.append("  <td id='links'>");
            if (this.withNavigation && !e.getLinkedObjects().isEmpty()) {
                Encoder urlEncoder = Base64.getUrlEncoder();
                for (MObject o : e.getLinkedObjects()) {
                    MRef mRef = new MRef(o);
                    String s = String.format("<a href='modelio:/element#%s:%s:%s'>%s</a> \n",
                            urlEncoder.encodeToString(mRef.mc.getBytes(StandardCharsets.UTF_8)),
                            urlEncoder.encodeToString(mRef.uuid.getBytes(StandardCharsets.UTF_8)),
                            urlEncoder.encodeToString(mRef.name.getBytes(StandardCharsets.UTF_8)),
                            mRef.name.isEmpty() ? "&lt;no name&gt;" : mRef.name);
                    html.append(s);
            
                }
            }
            html.append(String.format("</td>\n"));
            
        }

        @objid ("648ea142-93a2-46fa-b0a6-ed4f20663a8c")
        private void produceMessage(StringBuilder html, ReportEntry e) {
            String s = e.getMessage().replaceAll("\\n", "<br>\n");
            html.append(String.format("<div class=\"message\">%s</div>\n", s));
            
        }

        @objid ("90404cf4-b058-4d33-96f0-a086ebdc5ca0")
        private void produceCategory(StringBuilder html, ReportEntry e) {
            if (e.getCategory() != null && !e.getCategory().isEmpty()) {
                html.append(String.format("<span class=\"category\">[%s]</span>\n", e.getCategory()));
            }
            
        }

        @objid ("6fdd84af-1961-49ab-9632-2a154607f5cd")
        public HTMLReport withContents(boolean withErrors, boolean withWarnings, boolean withTips, boolean withInfos) {
            this.withErrors = withErrors;
            this.withWarnings = withWarnings;
            this.withTips = withTips;
            this.withInfos = withInfos;
            this.text = null;
            return this;
        }

        /**
         * @param mode 0 = events order, 1 = by severity, 2 = by code, 3 = by first associated element
         * @return the HTMLReport
         */
        @objid ("46818244-357f-4e50-8354-21213be5d3b6")
        public HTMLReport withSortMode(int mode) {
            this.sortMode = mode;
            this.text = null;
            return this;
        }

        @objid ("d40d3114-b7ae-44c5-a0ac-a746fe24964d")
        private List<ReportEntry> sortByElement(List<ReportEntry> entries) {
            List<ReportEntry> sortedEntries = new ArrayList<>(entries);
            sortedEntries.sort(new Comparator<ReportEntry>() {
                @Override
                public int compare(ReportEntry e1, ReportEntry e2) {
            
                    MObject o1 = e1.getLinkedObjects().isEmpty() ? null : e1.getLinkedObjects().get(0);
                    MObject o2 = e2.getLinkedObjects().isEmpty() ? null : e2.getLinkedObjects().get(0);
            
                    String key1 = o1 != null ? o1.getMClass().getQualifiedName() + o1.getName() : "";
                    String key2 = o2 != null ? o2.getMClass().getQualifiedName() + o2.getName() : "";
            
                    return key1.compareTo(key2);
                }
            });
            return sortedEntries;
        }

        @objid ("5879932d-2694-4755-93ec-f19bf6b2f613")
        private List<ReportEntry> sortByCode(List<ReportEntry> entries) {
            List<ReportEntry> sortedEntries = new ArrayList<>(entries);
            sortedEntries.sort(new Comparator<ReportEntry>() {
                @Override
                public int compare(ReportEntry e1, ReportEntry e2) {
                    return e1.getCode().compareTo(e2.getCode());
                }
            });
            return sortedEntries;
        }

        @objid ("fc49199e-eca4-46a3-bab1-2e7641470d69")
        private List<ReportEntry> sortBySeverity(List<ReportEntry> entries) {
            List<ReportEntry> sortedEntries = new ArrayList<>(entries);
            
            sortedEntries.sort(new Comparator<ReportEntry>() {
                @Override
                public int compare(ReportEntry e1, ReportEntry e2) {
                    return e1.getKind().compareTo(e2.getKind());
                }
            });
            return sortedEntries;
        }

        /**
         * Produce the 'code' cell as an hyperlink if an help url is defined or as raw text otherwise.
         * <p>
         * The help link which is produced uses a specialized href syntax to be later recognized by the browser location listener and intercepted to call the Modelio help system. <br/>
         * The href syntax is: <code>"modelio:/help#help topic url"</code>.
         * </p>
         */
        @objid ("d91666df-b4af-41a7-9527-c356bbfbaacb")
        private void produceCodeCell(StringBuilder html, ReportEntry e) {
            html.append("<td id='code'>");
            
            String helpUrl = e.getHelpUrl();
            if (this.withHelp && helpUrl != null && !helpUrl.isEmpty()) {
                try {
                    URI uri = new URI("modelio", null, "/help", null, helpUrl);
                    html.append(String.format("<a href='%s'>%s</a>", uri.toString(), e.getCode()));
                } catch (URISyntaxException e1) {
                    Api.LOG.error("Invalid help URI");
                    Api.LOG.debug(e1);
                }
            } else {
                html.append(String.format("%s", e.getCode()));
            }
            html.append("</td>\n");
            
        }

        @objid ("168bd010-384e-4686-a46b-3a1a13f75980")
        private void produceDiagnosticRow(StringBuilder html, ReportEntry e) {
            // New row
            html.append(String.format("<tr class='%s'>\n", e.getKind()));
            
            // Code cell
            produceCodeCell(html, e);
            
            // Message cell
            html.append("  <td id='message'>");
            html.append(e.getMessage());
            html.append("</td>\n");
            
            // Links cell
            produceLinksCell(html, e);
            
            // Category cell
            html.append("  <td id='category'>");
            String cat = e.getCategory();
            html.append(cat == null ? "" : cat);
            html.append("</td>\n");
            
            // End row
            html.append(String.format("</tr>\n"));
            
        }

        @objid ("61724b19-1102-47c9-b6d7-1308ff49e6d9")
        private void produceInfoRow(StringBuilder html, ReportEntry e) {
            if (this.withInfos) {
                html.append(String.format("<tr class='INFO'>\n<td id='info' colspan='4'>%s</td>\n</tr>\n", e.getMessage()));
            }
            
        }

    }

}
