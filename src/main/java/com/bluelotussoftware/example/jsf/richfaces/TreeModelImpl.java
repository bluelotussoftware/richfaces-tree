/*
 *  Copyright 2011-2013 Blue Lotus Software, LLC.
 *  Copyright 2011-2013 John Yeary <jyeary@bluelotussoftware.com>.
 *
 *  $Id$
 */
package com.bluelotussoftware.example.jsf.richfaces;

import java.io.Serializable;
import java.util.Iterator;
import javax.faces.convert.Converter;
import org.richfaces.model.TreeDataModel;
import org.richfaces.model.TreeDataModelTuple;
import org.richfaces.model.TreeNode;

/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 */
public class TreeModelImpl implements Serializable, TreeDataModel<TreeNode> {

    private static final long serialVersionUID = -7031007613774143944L;
    private TreeNode data;
    private Object rowkey;
    private Object wrappedData;

    public TreeModelImpl() {
    }

    @Override
    public Object getRowKey() {
        return rowkey;
    }

    @Override
    public void setRowKey(Object rowKey) {
        this.rowkey = rowKey;
    }

    @Override
    public boolean isDataAvailable() {
        return rowkey != null || data != null;
    }

    @Override
    public boolean isLeaf() {
        return data.isLeaf();
    }

    @Override
    public Iterator<TreeDataModelTuple> children() {
         throw new UnsupportedOperationException("Not supported yet.");
//        Iterator<?> children = Iterators.unmodifiableIterator(data.getChildrenKeysIterator());
//        return new ClassicTreeNodeTuplesIterator(data, new SequenceRowKey(data.));
    }

    @Override
    public Object getParentRowKey(Object rowKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getWrappedData() {
        return wrappedData;
    }

    @Override
    public void setWrappedData(Object data) {
        this.wrappedData = data;
    }

    @Override
    public TreeDataModelTuple createSnapshot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void restoreFromSnapshot(TreeDataModelTuple tuple) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Converter getRowKeyConverter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TreeNode getData() {
        return data;
    }

    public void setData(TreeNode data) {
        this.data = data;
    }
}
