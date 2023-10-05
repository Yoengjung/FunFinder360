package Utility;

public class Paging {
	private int totalCount = 0;
	private int totalPage = 0;
	private int pageNumber = 0;
	private int pageSize = 0;
	private int beginRow = 0;
	private int endRow = 0;
	private int pageCount = 10;
	private int beginPage = 0;
	private int endPage = 0;
	private String url = "";
	private String pagingHtml = "";
	private String pagingStatus = "";
	private String mode = "";
	private String keyword = "";
	private String flowParameter = "";
	
	public Paging(String _pageNumber, String _pageSize, int totalCount, String url, boolean isGrid) {
		if (_pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")) {
			_pageNumber = "1";
		}

		this.pageNumber = Integer.parseInt(_pageNumber);
		if (_pageSize == null || _pageSize.equals("null") || _pageSize.equals("")) {
			if (isGrid) {
				_pageSize = "8";
			} else {
				_pageSize = "10";
			}
		}
		
		this.pageSize = Integer.parseInt(_pageSize);
		this.totalCount = totalCount;
		this.url = url;
		double _totalPage = Math.ceil((double) totalCount / (double) this.pageSize);
		this.totalPage = (int) _totalPage;
		this.beginRow = (this.pageNumber - 1) * this.pageSize + 1;
		this.endRow = this.pageNumber * this.pageSize;
		if (this.endRow > totalCount) {
			this.endRow = totalCount;
		}

		this.beginPage = (this.pageNumber - 1) / this.pageCount * this.pageCount + 1;
		this.endPage = this.beginPage + this.pageCount - 1;
		if (this.endPage > this.totalPage) {
			this.endPage = this.totalPage;
		}

		this.pagingStatus = "총 " + totalCount + "건[" + this.pageNumber + "/" + this.totalPage + "]";
		this.flowParameter = "";
		this.flowParameter = this.flowParameter + "&pageNumber=" + this.pageNumber;
		this.flowParameter = this.flowParameter + "&pageSize=" + this.pageSize;
		this.pagingHtml = this.getMakePagingHtml();
		
		
	}
	
	public Paging(String _pageNumber, String _pageSize, int totalCount, String url, String mode, String keyword,
			boolean isGrid) {
		if (_pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")) {
			_pageNumber = "1";
		}

		this.pageNumber = Integer.parseInt(_pageNumber);
		if (_pageSize == null || _pageSize.equals("null") || _pageSize.equals("")) {
			if (isGrid) {
				_pageSize = "8";
			} else {
				_pageSize = "2";
			}
		}
		
		

		this.pageSize = Integer.parseInt(_pageSize);
		this.totalCount = totalCount;
		this.url = url;
		this.mode = mode == null ? "all" : mode;
		this.keyword = keyword == null ? "" : keyword;
		double _totalPage = Math.ceil((double) totalCount / (double) this.pageSize);
		this.totalPage = (int) _totalPage;
		this.beginRow = (this.pageNumber - 1) * this.pageSize + 1;
		this.endRow = this.pageNumber * this.pageSize;
		if (this.endRow > totalCount) {
			this.endRow = totalCount;
		}

		this.beginPage = (this.pageNumber - 1) / this.pageCount * this.pageCount + 1;
		this.endPage = this.beginPage + this.pageCount - 1;
		if (this.endPage > this.totalPage) {
			this.endPage = this.totalPage;
		}

		this.pagingStatus = "총 " + totalCount + "건[" + this.pageNumber + "/" + this.totalPage + "]";
		this.flowParameter = "";
		this.flowParameter = this.flowParameter + "&pageNumber=" + this.pageNumber;
		this.flowParameter = this.flowParameter + "&pageSize=" + this.pageSize;
		this.flowParameter = this.flowParameter + "&mode=" + mode;
		this.flowParameter = this.flowParameter + "&keyword=" + keyword;
		this.pagingHtml = this.getMakePagingHtml();
	}

	private String getMakePagingHtml() {
		String html = "";
		html = html + "<ul class=\"pagination justify-content-center\">";
		if (this.pageNumber > this.pageCount) {
			html = html + this.makeLiTag("맨처음", 1);
			html = html + this.makeLiTag("이전", this.beginPage - 1);
		}

		for (int i = this.beginPage; i <= this.endPage; ++i) {
			if (i == this.pageNumber) {
				html = html + "<li class=\"page-item active\">";
				html = html + "<a class=\"page-link\" href=\"#\">";
				html = html + "<b>" + i + "</b>";
				html = html + "</a></li>";
			} else {
				html = html + this.makeLiTag(String.valueOf(i), i);
			}
		}

		if (this.pageNumber < this.totalPage / this.pageCount * this.pageCount + 1) {
			html = html + this.makeLiTag("다음", this.endPage + 1);
			html = html + this.makeLiTag("맨끝", this.totalPage);
		}

		html = html + "</ul>";
		return html;
	}

	private String makeLiTag(String caption, int currPageNumber) {
		String result = "";
		result = result + "<li class='page-item'>";
		result = result + "<a class='page-link' href='";
		result = result + this.url;
		result = result + "&pageNumber=" + currPageNumber;
		result = result + "&pageSize=" + this.pageSize;
		result = result + "&mode=" + this.mode;
		result = result + "&keyword=" + this.keyword;
		result = result + "'>";
		result = result + caption;
		result = result + "</a></li>";
		return result;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBeginRow() {
		return this.beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getEndRow() {
		return this.endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPage() {
		return this.beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return this.endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPagingHtml() {
		return this.pagingHtml;
	}

	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public String getPagingStatus() {
		return this.pagingStatus;
	}

	public void setPagingStatus(String pagingStatus) {
		this.pagingStatus = pagingStatus;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFlowParameter() {
		return this.flowParameter;
	}

	public void setFlowParameter(String flowParameter) {
		this.flowParameter = flowParameter;
	}

	public String toString() {
		String imsi = "";
		imsi = imsi + "totalCount=" + this.totalCount + "<br/>";
		imsi = imsi + "totalPage=" + this.totalPage + "<br/>";
		imsi = imsi + "pageNumber=" + this.pageNumber + "<br/>";
		imsi = imsi + "pageSize=" + this.pageSize + "<br/>";
		imsi = imsi + "beginRow=" + this.beginRow + "<br/>";
		imsi = imsi + "endRow=" + this.endRow + "<br/>";
		imsi = imsi + "pageCount=" + this.pageCount + "<br/>";
		imsi = imsi + "beginPage=" + this.beginPage + "<br/>";
		imsi = imsi + "endPage=" + this.endPage + "<br/>";
		imsi = imsi + "url=" + this.url + "<br/>";
		imsi = imsi + "pagingStatus=" + this.pagingStatus + "<br/>";
		imsi = imsi + "mode=" + this.mode + "<br/>";
		imsi = imsi + "keyword=" + this.keyword + "<br/>";
		imsi = imsi + "flowParameter=" + this.flowParameter + "<br/>";
		imsi = imsi + "<br/><br/>";
		imsi = imsi + "pagingHtml=" + this.pagingHtml + "<br/>";
		return imsi;
	}
}
