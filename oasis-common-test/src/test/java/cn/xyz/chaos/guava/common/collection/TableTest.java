package cn.xyz.chaos.guava.common.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashBasedTable;

/**
 * {@link com.google.common.collect.Table}单元测试
 * <p />
 * Table是一种新的集合结构, 由行(row),列(column)和单元{value}组成
 *
 * @author lvchenggang
 *
 */
public class TableTest {

    private HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();

    @Before
    public void setUp() {
        table.put(1, 1, "Rook");
        table.put(1, 2, "Knight");
        table.put(1, 3, "Bishop");
        table.put(2, 1, "Lateral");
        table.put(2, 2, "L-Shape");
        table.put(2, 3, "Diagonal");
    }

    /**
     * {@link com.google.common.collect.Table#get(Object, Object)}测试
     */
    @Test
    public void testHashBasedTablePut() {
        assertThat(table.get(1, 2), is("Knight"));
        assertThat(table.get(5, 5), is(nullValue()));
    }

    /**
     * {@link com.google.common.collect.Table#column(Object)}测试
     */
    @Test
    public void testGetColumnView() {
        Map<Integer, String> columnMap = table.column(1);
        assertThat(columnMap.get(1), is("Rook"));
        assertThat(columnMap.get(2), is("Lateral"));
        columnMap.put(2, "Changed");
        assertThat(table.get(2, 1), is("Changed"));
        columnMap.put(3, "Added Value");
        assertThat(table.get(3, 1), is("Added Value"));
    }

    /**
     * {@link com.google.common.collect.Table#row(Object)}测试
     */
    @Test
    public void testGetRowView() {
        Map<Integer, String> rowMap = table.row(1);
        assertThat(rowMap.get(1), is("Rook"));
        assertThat(rowMap.get(2), is("Knight"));
        assertThat(rowMap.get(3), is("Bishop"));

        rowMap.put(4, "King");
        assertThat(table.get(1, 4), is("King"));
        assertThat(table.column(4).get(1), is("King"));
    }

    /**
     * {@link com.google.common.collect.Table#contains(Object, Object)}测试
     */
    @Test
    public void testTableOperations() {
        table.contains(1, 1);
        table.containsColumn(2);
        table.containsRow(1);
        table.containsValue("Rook");

    }
}
