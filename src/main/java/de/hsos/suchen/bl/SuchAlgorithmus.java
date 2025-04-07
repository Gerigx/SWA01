package de.hsos.suchen.bl;

public enum SuchAlgorithmus {
    KEYWORD_MATCHING,
    SEMANTIC_MATCHING;
    
    public static de.hsos.suchen.dal.WarenSuche getImplementation(SuchAlgorithmus algo) {
        switch (algo) {
            case KEYWORD_MATCHING:
                return new de.hsos.suchen.dal.KeywordMatching();
            case SEMANTIC_MATCHING:
                return new de.hsos.suchen.dal.SemanticMatching();
            default:
                return new de.hsos.suchen.dal.KeywordMatching(); // Default
        }
    }
}
