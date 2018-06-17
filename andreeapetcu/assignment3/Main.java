public class Main {

  public static void main (String[] args) {
    TestWordSearch testWordSearch = new TestWordSearch();
    testWordSearch.generalSetUp();

    testWordSearch.setUp1();
    
    testWordSearch.testGetNeighbours_normalCaseCornerElementPasses();
    testWordSearch.testGetNeighbours_normalCaseMiddleElementPasses();
    testWordSearch.testGetNeighbours_negativeRowOrColumnFails();
    testWordSearch.testGetNeighbours_nullGridFails();

    testWordSearch.setUp_depthFirstTraversal();
    testWordSearch.testDepthFirstTraversal_EmptyDictionaryPasses();
    testWordSearch.testDepthFirstTraversal_NullDictionaryFails();
    testWordSearch.testDepthFirstTraversal_NullGridFails();
    testWordSearch.testDepthFirstTraversal_NullStartingPointFails();
    testWordSearch.testDepthFirstTraversal_nullVisitedFails();
    testWordSearch.testDepthFirstTraversal_nullWordsAccumulatorFails();
    testWordSearch.testDepthFirstTraversal_validInputPasses();

    testWordSearch.testCreateTrieGrid_negativeRowOrColumnFails();
    testWordSearch.testCreateTrieGrid_nullGridFails();
    testWordSearch.testCreateTrieGrid_validInputPasses();

    testWordSearch.testIsWithinBoundaries_negativeRowOrColumnBoundaryFails();
    testWordSearch.testIsWithinBoundaries_negativeRowOrColumnFails();
    testWordSearch.testIsWithinBoundaries_RowGreaterThanRowBoundaryFails();

    testWordSearch.setUp_getStringFromRootToNode();
    testWordSearch.testGetStringFromRootToNode_nullNodeFails();
    testWordSearch.testGetStringFromRootToNode_validNodePasses();

    testWordSearch.testWordSearch_nullDictionaryFails();
    testWordSearch.testWordSearch_emptyDictionaryPasses();
    testWordSearch.testWordSearch_nullGridFails();
    testWordSearch.testWordSearch_validInputPassesSetUp1();
    
    testWordSearch.setUp2();
    testWordSearch.testWordSearch_validInputPassesSetUp2();
  }
}
