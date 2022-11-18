import './static/Home.css';
import {Link, NavLink, useNavigate} from "react-router-dom";
import {ReactComponent as Logo} from "./static/undraw_online_cv_re_gn0a.svg";
import {ReactComponent as X} from "./static/x.svg";

function Home() {
    return (
        <div>
            <div className="container mt-5">
                <div className="row">
                    <div className="col-lg-8">
                        <form>
                            <div id="fields" className="mb-3">
                                <div className="row">
                                    <div className="col-4">
                                        <h5>FieldName</h5>
                                    </div>
                                    <div className="col-4">
                                        <h5>Type</h5>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-4">
                                        <input type="text" className="form-control"/>
                                    </div>
                                    <div className="col-4">
                                        <select className="form-select">
                                            <option selected>Choose type</option>
                                            <option value="ID">ID</option>
                                            <option value="UUID">UUID</option>
                                            <option value="NAME">NAME</option>
                                        </select>
                                    </div>
                                    <div className="col-4">
                                        <button className="btn btn-outline-dark">
                                            <X width="100%"/>
                                        </button>
                                    </div>
                                </div>
                                <button className="btn btn-dark mt-3">Add Field</button>
                            </div>

                            <div className="mb-3">
                                <label htmlFor="FileName1" className="form-label">File Name</label>
                                <input type="text" className="form-control" id="FileName1"/>
                            </div>

                            <div className="mb-3">
                                <label htmlFor="exampleInputPassword1" className="form-label">Row Count</label>
                                <input type="number" className="form-control" id="exampleInputPassword1"/>
                            </div>

                            <label htmlFor="select1" className="form-label">Choose Format</label>
                            <select className="form-select" id="select1">
                                <option value="SQL" selected>SQL</option>
                                <option value="JSON">JSON</option>
                                <option value="CSV">CSV</option>
                            </select>

                            <button type="submit" className="btn btn-primary mt-3">Download</button>
                        </form>
                    </div>
                    <div className="col-lg-4">

                    </div>
                </div>
            </div>
            {/*<Logo/>*/}
        </div>
    )
}

export default Home