/*
 *  Copyright 2011-2013 Blue Lotus Software, LLC.
 *  Copyright 2011-2013 John Yeary <jyeary@bluelotussoftware.com>.
 *
 *  $Id$
 */
package com.bluelotussoftware.example.jsf.richfaces;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.faces.convert.Converter;
import org.richfaces.convert.StringSequenceRowKeyConverter;
import org.richfaces.model.NodesTreeSequenceKeyModel;
import org.richfaces.model.TreeDataModelTuple;
import org.richfaces.model.iterators.ClassicTreeNodeTuplesIterator;

/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 */
public class NodesTreeSequenceKeyModelImpl<T> extends NodesTreeSequenceKeyModel<TreeNodeImpl<T>> implements Serializable{

    private static final Converter DEFAULT_CONVERTER = new StringSequenceRowKeyConverter();

    @Override
    protected TreeNodeImpl<T> setupChildContext(Object segment) {
        return getData().getChild(segment);
    }

    @Override
    public Object getWrappedData() {
        return getRootNode();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setWrappedData(Object data) {

        TreeNodeImpl<T> nodeRoot;
        if (data instanceof List<?>) {
            nodeRoot = new TreeNodeImpl<T>();
            int i = 0;
            for (TreeNodeImpl<T> node : ((List<TreeNodeImpl<T>>) data)) {
                nodeRoot.addChild(i++, node);
            }
            setRootNode(nodeRoot);
        } else {
            setRootNode((TreeNodeImpl<T>) data);
        }
    }

    @Override
    public boolean isLeaf() {
        return getData().isLeaf();
    }

    @Override
    public Iterator<TreeDataModelTuple> children() {
        return new ClassicTreeNodeTuplesIterator(getData(), getRowKey());
    }

    @Override
    public Converter getRowKeyConverter() {
        return DEFAULT_CONVERTER;
    }

    protected TreeNodeImpl<T> findChild(TreeNodeImpl<T> parent, int segment) {
        return parent.getChild(getData().getKeys().get(segment));
    }
}
