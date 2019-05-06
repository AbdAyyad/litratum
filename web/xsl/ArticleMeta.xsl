<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="article">
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="author"/>
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="article-doi"/>
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="release-date"/>
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="article-title"/>
    </xsl:template>

    <xsl:template name="author">
        <xsl:value-of select="front/article-meta/contrib-group/contrib/string-name/given-names"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="front/article-meta/contrib-group/contrib/string-name/surname"/>
    </xsl:template>

    <xsl:template name="article-doi">
        <xsl:for-each select="front/article-meta/article-id">
            <xsl:if test="@pub-id-type = 'publisher-id'">
                <xsl:value-of select="text()"/>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="release-date">
        <xsl:value-of select="front/article-meta/pub-date/month"/>
        <xsl:text>/</xsl:text>
        <xsl:value-of select="front/article-meta/pub-date/year"/>
    </xsl:template>

    <xsl:template name="article-title">
        <xsl:value-of select="front/article-meta/title-group/article-title"/>
    </xsl:template>

</xsl:stylesheet>