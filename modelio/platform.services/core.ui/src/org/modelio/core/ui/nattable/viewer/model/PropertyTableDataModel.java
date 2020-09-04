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

package org.modelio.core.ui.nattable.viewer.model;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.IRowDataProvider;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("9e87afe3-9712-4242-abf9-96869a7bbd4e")
public class PropertyTableDataModel {
    @objid ("c29e5777-5071-43ed-acd9-0af36f2443a1")
    protected final List<Integer> columns = new ArrayList<>();

    @objid ("402283d4-bad0-43b0-9103-aee77ec5ab32")
    protected List<Integer> rows = new ArrayList<>();

    @objid ("adde0cdd-aa58-4ef6-8526-e3a1f02d2bf8")
    protected final BodyDataProvider bodyDataProvider;

    @objid ("fd7d89e8-1f5e-40b1-ae9e-f7fe3f12d53d")
    protected final RowHeaderDataProvider rowHeaderDataProvider;

    @objid ("bec424eb-b66d-4b34-8012-12a22f6c3714")
    protected final ColumnHeaderDataProvider columnHeaderDataProvider;

    @objid ("6c9d67a1-4530-41f3-a57c-7b91f38b4d51")
    protected final IPropertyModel<?> propertyModel;

    @objid ("7beafeff-5cb9-4544-8ae5-38cbc506095b")
    public List<Integer> getRows() {
        return this.rows;
    }

    @objid ("1b6421d6-7508-4f0b-8140-75b8e729de9b")
    public Iterable<Integer> getColumns() {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < this.propertyModel.getColumnNumber() ; i++) {
            ret.add(i);
        }
        return ret;
    }

    @objid ("a0d0db31-5f05-4730-9e8d-a2845a5958d0")
    public PropertyTableDataModel(IPropertyModel<?> propertyModel) {
        this.propertyModel = propertyModel;
        
        this.bodyDataProvider = new BodyDataProvider(this);
        this.columnHeaderDataProvider = new ColumnHeaderDataProvider(this);
        this.rowHeaderDataProvider = new RowHeaderDataProvider(this);
        
        // set data
        rebuildData();
    }

    @objid ("fe641d0e-2df1-4f53-904d-a9d134aaca80")
    public IPropertyModel<?> getPropertyModel() {
        return this.propertyModel;
    }

    /**
     * @return the table main data
     */
    @objid ("6d906a41-75f7-4219-ad7d-25d9a4003a14")
    public IDataProvider getBodyDataProvider() {
        return this.bodyDataProvider;
    }

    /**
     * @return the table row headers data
     */
    @objid ("65676f3e-931d-4ebf-9c84-cff866538413")
    public IDataProvider getRowHeaderDataProvider() {
        return this.rowHeaderDataProvider;
    }

    /**
     * @return the table column headers data
     */
    @objid ("c0c4e43a-d2ae-4a92-9338-fa914849659d")
    public IDataProvider getColumnHeaderDataProvider() {
        return this.columnHeaderDataProvider;
    }

    @objid ("0398da5a-6acf-49ae-8f23-da035e27a152")
    public void rebuildData() {
        // Rows
        this.rows.clear();
        for (int i = 0; i < this.propertyModel.getRowsNumber() ; i++) {
            this.rows.add(i);
        }
        
        // Columns
        this.columns.clear();
        for (final Integer elt : getColumns()) {
            this.columns.add(elt);
        }
    }

    @objid ("f64a89b8-4b2b-4be9-9eec-6af620de4d34")
    public Integer getObjectAtRow(int rowIndex) {
        return rowIndex >= 0 ? this.rows.get(rowIndex) : null;
    }

    @objid ("f05bb3e8-8daa-40e9-838d-7ad1455774f0")
    public Integer getObjectAtCol(int colIndex) {
        return colIndex >= 0 ? this.columns.get(colIndex) : null;
    }

    @objid ("91150661-80f5-468a-aff8-3ac82a54c44b")
    public Integer getRowIndex(Object o) {
        return this.rows.indexOf(o);
    }

    @objid ("58c5032e-c224-4973-9e10-b0d408ab3577")
    public int getColumnIndex(Object o) {
        return this.columns.indexOf(o);
    }

    @objid ("984b4fc4-107f-4ce6-a045-e62a18c24ee2")
    public Object getValueAt(int row, int col) {
        return this.propertyModel.getValueAt(getObjectAtRow(row), getObjectAtCol(col));
    }

    @objid ("aa9c8a5d-42f5-4528-8682-da1ed88f2844")
    public void setValueAt(int row, int col, Object val) {
        this.propertyModel.setValueAt(getObjectAtRow(row), getObjectAtCol(col), val);
    }

    @objid ("6e2c2efc-c0ab-4d37-a7bc-a0da20b3d789")
    private static class BodyDataProvider implements IDataProvider {
        @objid ("9b074009-35da-4edb-8b58-7622ad80f902")
        private final PropertyTableDataModel base;

        @objid ("c5b33121-ea4f-4338-a2f5-774d25cebb0e")
        public BodyDataProvider(PropertyTableDataModel base) {
            this.base = base;
        }

        @objid ("7487c6a6-6b30-4997-b038-6ba75e48db09")
        @Override
        public int getColumnCount() {
            return this.base.columns.size();
        }

        @objid ("d5195268-2ae2-4773-b21b-1cd5952f0dcc")
        @Override
        public Object getDataValue(int col, int row) {
            //final Object elt = this.base.sortedRows.get(row);
            return this.base.getValueAt(1 + row, col);
        }

        @objid ("cc0532b8-aec5-4b37-addc-a0f151ecf77c")
        @Override
        public int getRowCount() {
            return this.base.rows.size() - 1;
        }

        @objid ("6d03683b-ed18-4743-aa32-92b9d1990a9e")
        @Override
        public void setDataValue(int col, int row, Object value) {
            final MObject editedElement = this.base.propertyModel.getEditedElement();
            final CoreSession session = CoreSession.getSession(editedElement);
            final Object pname = this.base.getPropertyModel().getValueAt(row+1, 0).getValue();
            try (ITransaction t = session.getTransactionSupport().createTransaction(String.format("Update %s '%s' to : %s", editedElement, pname, value))) {
                // code
                this.base.setValueAt(1 + row, col, value);
            
                // Commit transaction
                t.commit();
            } catch (RuntimeException | LinkageError e) {
                // Log ourself the exception, nattable uses apache commons logger which is not configured
                CoreUi.LOG.error(e);
                throw e;
            }
        }

    }

    /**
     * @author phv
     */
    @objid ("d2e92d1d-4851-446b-8a88-111d0cef0bab")
    private static class ColumnHeaderDataProvider implements IDataProvider {
        @objid ("daac6210-c116-4b82-b1b0-9709340ecc0a")
        private final PropertyTableDataModel base;

        @objid ("e35f7928-85ef-4afb-b7b8-f04214f84410")
        public ColumnHeaderDataProvider(PropertyTableDataModel base) {
            this.base = base;
        }

        @objid ("cf43e955-a00d-4fec-bece-cfdbc6916c00")
        @Override
        public int getColumnCount() {
            return this.base.getBodyDataProvider().getColumnCount();
        }

        @objid ("5a4199b5-9a2e-4b66-82d3-cd90e063ccd0")
        @Override
        public Object getDataValue(int col, int row) {
            return this.base.getValueAt(0, col);
            //return this.base.getObjectAtCol(col);
        }

        @objid ("43d1817d-a613-4476-b169-cd098a375b3a")
        @Override
        public int getRowCount() {
            return 1;
        }

        @objid ("9426705f-96a6-4b42-a8dc-0ce0db0fc371")
        @Override
        public void setDataValue(int col, int row, Object value) {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("5e52abb7-a76d-4a9f-901c-84987a8e03ce")
    private static class RowHeaderDataProvider implements IRowDataProvider<Object> {
        @objid ("8e889bd1-1048-4601-81eb-6e385649d29b")
        private final PropertyTableDataModel base;

        @objid ("e22304ca-75a6-41e6-99e2-ba12a7dbb338")
        public RowHeaderDataProvider(PropertyTableDataModel base) {
            this.base = base;
        }

        @objid ("217376fd-ab93-4b66-9954-f3d947e0de21")
        @Override
        public int getColumnCount() {
            return 1;
        }

        @objid ("fdf89958-e3e5-4455-8931-2a0987a1cc50")
        @Override
        public Object getDataValue(int col, int row) {
            return this.base.getValueAt(1+row, 0);
            //return this.base.getObjectAtRow(1 + row);
        }

        @objid ("deebeaae-3f10-4cd4-a8e5-d830ce35f884")
        @Override
        public int getRowCount() {
            return this.base.getBodyDataProvider().getRowCount();
        }

        @objid ("5fcf8427-3d77-4943-a7b8-31da80ce3612")
        @Override
        public void setDataValue(int arg0, int arg1, Object arg2) {
            throw new UnsupportedOperationException();
        }

        @objid ("38089769-6dd7-4309-9311-c129896bc0ab")
        @Override
        public Object getRowObject(int rowIndex) {
            return this.base.getObjectAtRow(rowIndex);
        }

        @objid ("b10f0913-45db-4584-b186-48b2246538ef")
        @Override
        public int indexOfRowObject(Object rowObject) {
            return this.base.getRowIndex(rowObject);
        }

    }

}
